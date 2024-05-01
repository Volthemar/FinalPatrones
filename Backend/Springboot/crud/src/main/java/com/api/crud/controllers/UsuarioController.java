package com.api.crud.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.api.crud.services.IEmailService;
import com.api.crud.services.UsuarioService;
import com.api.crud.services.models.EmailDTO;

import jakarta.mail.MessagingException;

import com.api.crud.models.UsuarioModel;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usuario")

public class UsuarioController {
    @Autowired
    private UsuarioService userService;
    @Autowired
    IEmailService emailService;

    @GetMapping("/getUsuarios")
    public ResponseEntity<String> getUsuarios() throws MessagingException{
        EmailDTO email = new EmailDTO();
        email.setMensaje("123456");
        email.setAsunto("prueba 1");
        email.setDestinatario("davariasc@udistrital.edu.co");
        emailService.enviarCorreo(email);
        return new ResponseEntity<>("Correo enviado exitosamente",HttpStatus.OK);
    }

    @PostMapping("/postUsuario")
    public UsuarioModel postGuardarUsuario(@RequestBody UsuarioModel usuario) {
        return this.userService.guardarUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Optional<UsuarioModel> getUsuariosPorId(@PathVariable Long id) {
        return this.userService.getPorId(id);
    }

}
