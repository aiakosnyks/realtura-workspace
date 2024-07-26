package com.realtura.realturamain.config;

import com.realtura.realturamain.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfig {
    @Bean
    public Product product() {
        return new Product();
    }
}
