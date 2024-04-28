package com.api.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.crud.DTO.LoginRequest;
import com.api.crud.services.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UsuarioService userService;

    @PostMapping("")
    public Map<String,Object> login(@RequestBody LoginRequest loginRequest) {
        String usuario = loginRequest.getUsuario();
        String contrasena = loginRequest.getContrasena();

        if (!this.userService.login(usuario,contrasena).isEmpty()){
            return Map.of("data",this.userService.login(usuario,contrasena),"status",HttpStatus.valueOf(200),"msg","El usuario existe");
        }else{
            return Map.of("data","","status",HttpStatus.valueOf(200),"msg","Usuario o contraseña incorrecta");
        }
        
    }
}
