package com.carreiras.java_spring_boot_pedidos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.text.ParseException;

import com.carreiras.java_spring_boot_pedidos.service.DBService;
import com.carreiras.java_spring_boot_pedidos.service.EmailService;
import com.carreiras.java_spring_boot_pedidos.service.MockEmailService;

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

//    @Bean
//    public TemplateEngine templateEngine() {
//        return new TemplateEngine();
//    }
}
