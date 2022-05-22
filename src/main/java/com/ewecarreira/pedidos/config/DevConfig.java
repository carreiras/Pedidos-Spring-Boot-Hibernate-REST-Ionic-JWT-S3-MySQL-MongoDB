package com.ewecarreira.pedidos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.TemplateEngine;

import java.text.ParseException;

import com.ewecarreira.pedidos.service.DBService;
import com.ewecarreira.pedidos.service.EmailService;
import com.ewecarreira.pedidos.service.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        if (!"create".equals(strategy)) {
            return false;
        }

        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }

//    @Bean
//    public TemplateEngine templateEngine() {
//        return new TemplateEngine();
//    }
}
