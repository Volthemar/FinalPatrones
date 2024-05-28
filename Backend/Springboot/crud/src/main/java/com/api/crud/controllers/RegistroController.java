package com.api.crud.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.crud.dto.request.RegistroPersonaRequest;
import com.api.crud.services.UsuarioService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("")
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/registroPersona")
    public Map<String, Object> registroPersona(@RequestBody RegistroPersonaRequest registroPersona) throws MessagingException {
        return usuarioService.registrarUsuario(registroPersona);
    }
}
