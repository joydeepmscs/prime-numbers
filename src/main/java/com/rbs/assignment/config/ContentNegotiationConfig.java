package com.rbs.assignment.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class ContentNegotiationConfig extends WebMvcConfigurerAdapter {

    /**
     *  Total customization - see below for explanation.
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true).
                favorParameter(false).
                parameterName("mediaType").
                ignoreAcceptHeader(false).
                defaultContentType(MediaType.APPLICATION_JSON).
                mediaType("xml", MediaType.APPLICATION_XML).
                mediaType("html", MediaType.TEXT_HTML).
                mediaType("json", MediaType.APPLICATION_JSON);
    }
}