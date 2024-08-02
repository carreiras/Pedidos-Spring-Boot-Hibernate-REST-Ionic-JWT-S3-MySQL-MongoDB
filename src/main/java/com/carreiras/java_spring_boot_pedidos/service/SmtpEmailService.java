package com.carreiras.java_spring_boot_pedidos.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(SimpleMailMessage message) {
        LOG.info("Enviando e-mail...");
        mailSender.send(message);
        LOG.info("E-mail enviado.");
    }

    @Override
    public void sendHtmlEmail(MimeMessage message) {
        LOG.info("Enviando e-mail...");
        javaMailSender.send(message);
        LOG.info("E-mail enviado.");
    }
}
