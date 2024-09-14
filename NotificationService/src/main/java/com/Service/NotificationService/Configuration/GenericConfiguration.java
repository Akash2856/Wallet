package com.Service.NotificationService.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenericConfiguration {
    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();//Convert String to Json and vice versa
    }
}
