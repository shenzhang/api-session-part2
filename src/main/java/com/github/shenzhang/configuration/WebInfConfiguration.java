package com.github.shenzhang.configuration;

import com.github.shenzhang.configuration.filter.CorsFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import static com.google.common.collect.Sets.newHashSet;

/**
 * User: shenzhang
 * Date: 11/7/14
 * Time: 1:57 PM
 */
@Configuration
public class WebInfConfiguration implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new CorsFilter());
        bean.setUrlPatterns(newHashSet("/*"));
        return bean;
    }

    /*
    @Bean
    public ServletRegistrationBean defaultServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean();
        // configure bean ...
        return bean;
    }
    */
}
