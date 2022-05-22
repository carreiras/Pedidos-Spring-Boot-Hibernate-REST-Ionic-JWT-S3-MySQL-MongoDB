package com.ewecarreira.pedidos.service;

import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

import com.ewecarreira.pedidos.domain.entity.Cliente;
import com.ewecarreira.pedidos.domain.entity.Pedido;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage message);

    void sendOrderConfirmationHtmlEmail(Pedido pedido);

    void sendHtmlEmail(MimeMessage message);

    void sendNewPasswordEmail(Cliente cliente, String newPass);
}
