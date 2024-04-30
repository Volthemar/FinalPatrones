package com.api.crud.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.models.UsuarioModel;
import com.api.crud.repositories.IUsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    IUsuarioRepository userRepository;

    public ArrayList<UsuarioModel> getUser() {
        return (ArrayList<UsuarioModel>) userRepository.findAll();
    }

    public UsuarioModel guardarUsuario(UsuarioModel usuario) {
        return userRepository.save(usuario);
    }

    public Optional<UsuarioModel> getPorId(Long id) {
        return userRepository.findById(id);
    }

    public Optional<UsuarioModel> login(String usuario, String contrasena) {
        return userRepository.findByUsuarioAndContrasena(usuario, contrasena);
    }

}
