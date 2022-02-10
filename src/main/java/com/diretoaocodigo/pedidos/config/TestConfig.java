package com.diretoaocodigo.pedidos.config;

import com.diretoaocodigo.pedidos.service.DBService;
import com.diretoaocodigo.pedidos.service.EmailService;
import com.diretoaocodigo.pedidos.service.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.TemplateEngine;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }

    @Bean
    public TemplateEngine templateEngine() {
        return new TemplateEngine();
    }
}
