package com.ua.goit.gojava7.ryzhkov.finalproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Set;

@Configuration
public class ConversionServiceConfig extends WebMvcConfigurerAdapter {

    @Bean
    @Autowired
    public ConversionServiceFactoryBean conversionService(Set<Converter> converters) {
        ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
        factory.setConverters(converters);
        return factory;
    }

}
