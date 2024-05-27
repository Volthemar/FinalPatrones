package com.api.crud.services;

import com.api.crud.services.models.EmailCupo;
import com.api.crud.services.models.EmailDTO;

import jakarta.mail.MessagingException;

public interface IEmailService {
    public void enviarCorreoCodigo(EmailDTO email) throws MessagingException;
    public void enviarCorreoRegistro(EmailDTO email) throws MessagingException;
    public void enviarCorreoBloqueo(EmailDTO email) throws MessagingException;
    public void enviarCorreoTarjeta(EmailDTO email) throws MessagingException;
    public void enviarCorreoCodigoCupo(EmailCupo email) throws MessagingException;
    public void enviarCorreoConfirmacionCupo(EmailCupo email) throws MessagingException;
}
