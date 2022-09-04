package com.jerrycodes.emis;

import com.jerrycodes.emis.entity.Employee;
import com.jerrycodes.emis.model.UserModel;
import com.jerrycodes.emis.service.UserService;
import lombok.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.UUID;

@SpringBootApplication
@EnableSwagger2
@EnableWebSecurity
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

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            userService.saveUser(new UserModel("jerry", "1234", "jerry@woodcoreapp.com"));
            userService.saveUser(new UserModel("Blaze", "3457", "blaze@woodcoreapp.com"));
            userService.saveUser(new UserModel("bode", "9876", "bode@woodcoreapp.com"));
            userService.saveUser(new UserModel("amilo", "87987", "amilo@woodcoreapp.com"));
        };
    }
}
