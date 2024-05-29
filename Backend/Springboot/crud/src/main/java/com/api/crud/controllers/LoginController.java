package com.api.crud.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;

import com.api.crud.services.UsuarioService;
import com.api.crud.dto.request.LoginRequest;
import com.api.crud.dto.request.LoginCodigoRequest;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("")
public class LoginController {
    @Autowired
    private UsuarioService userService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest loginRequest) throws MessagingException {
        return userService.manejarLogin(loginRequest);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/loginCodigo")
    public Map<String, Object> loginCodigo(@RequestBody LoginCodigoRequest loginCodigoRequest, HttpServletRequest request) {
        return userService.manejarLoginCodigo(loginCodigoRequest, request);
    }
}


