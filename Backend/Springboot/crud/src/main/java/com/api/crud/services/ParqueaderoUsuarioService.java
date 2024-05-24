package com.api.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.repositories.IParqueaderoUsuarioRepository;

@Service
public class ParqueaderoUsuarioService {
    @Autowired
    private IParqueaderoUsuarioRepository parqueaderoUsuarioRepository;

    public Long obtenerIdParqueadero(Long usuario){
        return parqueaderoUsuarioRepository.findByUsuario(usuario);
    }

}
