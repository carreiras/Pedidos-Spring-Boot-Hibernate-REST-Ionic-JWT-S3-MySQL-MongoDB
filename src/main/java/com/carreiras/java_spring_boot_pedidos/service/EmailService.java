package com.carreiras.java_spring_boot_pedidos.service;

import com.carreiras.java_spring_boot_pedidos.domain.entity.Cliente;
import com.carreiras.java_spring_boot_pedidos.domain.entity.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage message);

    void sendOrderConfirmationHtmlEmail(Pedido pedido);

    void sendHtmlEmail(MimeMessage message);

    void sendNewPasswordEmail(Cliente cliente, String newPass);
}
