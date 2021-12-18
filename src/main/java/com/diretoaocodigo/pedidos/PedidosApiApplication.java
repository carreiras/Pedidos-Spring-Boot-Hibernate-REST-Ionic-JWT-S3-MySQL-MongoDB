package com.diretoaocodigo.pedidos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PedidosApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PedidosApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
