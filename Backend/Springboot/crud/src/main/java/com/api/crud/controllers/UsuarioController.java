package com.api.crud.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.api.crud.services.UsuarioService;
import com.api.crud.models.UsuarioModel;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usuario")

public class UsuarioController {
    @Autowired
    private UsuarioService userService;

    @GetMapping("/getUsuarios")
    public String getUsuarios() {
        // Aqui voy a poner la fuuncion pa crear correos
        EnviarCodigo ec = new EnviarCodigo();
        ec.sendVerificationCodeByEmail("davidariascalderon04@gmail.com", "hola");
        return "hola";
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
