package com.carreiras.pedidos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PedidosApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PedidosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
