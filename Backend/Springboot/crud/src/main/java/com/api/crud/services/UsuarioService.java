package com.api.crud.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.models.UsuarioModel;
import com.api.crud.repositories.IUsuarioRepository;
import java.util.List;

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

    public Optional<UsuarioModel> buscarUsuario(String usuario) {
        return userRepository.findByUsuario(usuario);
    }

    public List<UsuarioModel> getAllUsuarios() {
        return userRepository.findAll();
    }

    public String codigoUsuario(Long id) {
        return userRepository.findCodigoForId(id);
    }

    @Autowired
    private IUsuarioRepository IUsuarioRepository;

    public Optional<UsuarioModel> actualizarEstado(long id, boolean estado) {
        Optional<UsuarioModel> usuario = IUsuarioRepository.findById(id);
        if (usuario.isPresent()) {
            UsuarioModel usuarioModel = usuario.get();
            usuarioModel.setEstado(estado);
            IUsuarioRepository.save(usuarioModel);
            return Optional.of(usuarioModel);
        } else {
            return Optional.empty();
        }
    }

    public UsuarioModel updateEstado(Long id, Boolean estado) {
        Optional<UsuarioModel> usuarioOpt = IUsuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            UsuarioModel usuario = usuarioOpt.get();
            usuario.setEstado(estado);
            return IUsuarioRepository.save(usuario);
        }
        return null;
    }

    public List<UsuarioModel> getUsuariosPorEstado(boolean estado) {
        return IUsuarioRepository.findByEstado(estado);
    }

    public List<UsuarioModel> getUsuarios() {
        return IUsuarioRepository.findAll();
    }

    public UsuarioModel updateEstadoToNull(long id) {
        UsuarioModel usuario = IUsuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setEstado(null); // Establecer el estado como null
            return IUsuarioRepository.save(usuario);
        }
        return null; // Usuario no encontrado
    }

}