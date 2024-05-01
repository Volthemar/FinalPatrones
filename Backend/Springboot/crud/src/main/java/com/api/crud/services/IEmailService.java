package com.api.crud.services;

import com.api.crud.services.models.EmailDTO;

import jakarta.mail.MessagingException;

public interface IEmailService {
    public void enviarCorreo(EmailDTO email) throws MessagingException;
}
