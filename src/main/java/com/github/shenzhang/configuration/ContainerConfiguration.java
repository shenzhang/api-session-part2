package com.github.shenzhang.configuration;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * User: shenzhang
 * Date: 11/7/14
 * Time: 2:11 PM
 */

//@Configuration
public class ContainerConfiguration implements EmbeddedServletContainerCustomizer {
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8080);
    }

    /**
     * deep customize embedded web containner.
     * could change tomcat to jetty here
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.addAdditionalTomcatConnectors(createSslConnector());
        return factory;
    }

    private Connector createSslConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        try {
            File keystore = getKeyStoreFile();
            File truststore = keystore;
            connector.setScheme("https");
            connector.setSecure(true);
            connector.setPort(8443);
            protocol.setSSLEnabled(true);
            protocol.setKeystoreFile(keystore.getAbsolutePath());
            protocol.setKeystorePass("123456");
            protocol.setTruststoreFile(truststore.getAbsolutePath());
            protocol.setTruststorePass("123456");
            protocol.setKeyAlias("fish");
            return connector;
        } catch (IOException ex) {
            throw new IllegalStateException("cant access keystore: [" + "keystore"
                    + "] or truststore: [" + "keystore" + "]", ex);
        }
    }

    private File getKeyStoreFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("keystore");
        try {
            return resource.getFile();
        } catch (Exception ex) {
            File temp = File.createTempFile("keystore", ".tmp");
            FileCopyUtils.copy(resource.getInputStream(), new FileOutputStream(temp));
            return temp;
        }
    }
}
