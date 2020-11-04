package org.edu.config;


import org.edu.commonutil.OssTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @ConfigurationProperties(prefix = "oss")
    @Bean
    public OssTemplate ossTemplate(){
        return new OssTemplate();
    }

}
