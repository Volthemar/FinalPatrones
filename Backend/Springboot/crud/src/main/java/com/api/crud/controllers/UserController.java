package com.api.crud.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.api.crud.services.UserService;
import com.api.crud.models.UserModel;

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

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUsuarios")
    public ArrayList<UserModel> getUsuarios() {
        return this.userService.getUser();
    }

    @PostMapping("/postUsuario")
    public UserModel postGuardarUsuario(@RequestBody UserModel usuario) {
        return this.userService.guardarUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Optional<UserModel> getUsuariosPorId(@PathVariable Long id) {
        return this.userService.getPorId(id);
    }

}
