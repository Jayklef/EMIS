package com.jerrycodes.emis;

import com.jerrycodes.emis.entity.Employee;
import lombok.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@SpringBootApplication
public class EmisApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmisApplication.class, args);
    }

    @Bean
    public String generateStaffId(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);

        return uuid.toString();
    }
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

}
