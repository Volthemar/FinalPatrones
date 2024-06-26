package com.api.crud.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.crud.dto.request.UsuarioRequest;
import com.api.crud.services.ParqueaderoUsuarioService;

@RestController
@RequestMapping("")
public class ParqueaderoUsuarioController {
    @Autowired
    private ParqueaderoUsuarioService parqueaderoUsuarioService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/parqueaderoAdmi")
    public Map<String, Object> parqueaderoAdmi(@RequestBody UsuarioRequest usuario) {
        return parqueaderoUsuarioService.obtenerInfoParqueaderoUsuario(usuario);
    }
}
