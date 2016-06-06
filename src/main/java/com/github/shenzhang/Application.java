package com.github.shenzhang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * User: shenzhang
 * Date: 3/27/14
 * Time: 2:13 PM
 */

@ComponentScan
@SpringBootApplication
@PropertySource({"classpath:application.properties"})
public class Application extends SpringBootServletInitializer {
    @Autowired
    private Environment environment;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
