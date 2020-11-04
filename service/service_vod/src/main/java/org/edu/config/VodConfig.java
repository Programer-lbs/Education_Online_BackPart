package org.edu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VodConfig {

    @ConfigurationProperties(prefix = "vod")
    @Bean
    public VodTemplate vodTemplate(){
        return new VodTemplate();
    }
}
