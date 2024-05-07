package com.api.crud.services.impl;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.api.crud.services.IEmailService;
import com.api.crud.services.models.EmailDTO;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements IEmailService{
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void enviarCorreo(EmailDTO email) throws MessagingException{
        try{
            MimeMessage mensaje = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje,true,"utf-8");
            helper.setTo(email.getDestinatario());
            helper.setSubject(email.getAsunto());
            Context context = new Context();
            context.setVariable("mensaje", email.getMensaje());
            String contenidoHTML = templateEngine.process("enviarCodigo", context);
            helper.setText(contenidoHTML,true);
            javaMailSender.send(mensaje);
        }catch (Exception e){
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage(), e);
        }
    }

}