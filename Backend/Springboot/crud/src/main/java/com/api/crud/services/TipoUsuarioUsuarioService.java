package com.api.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import com.api.crud.models.TipoUsuarioUsuarioModel;
import com.api.crud.repositories.ITipoUsuarioUsuarioRepository;

@Service
public class TipoUsuarioUsuarioService {
    @Autowired
    private ITipoUsuarioUsuarioRepository tipoUsuarioUsuarioRepository;

    public ArrayList<TipoUsuarioUsuarioModel> obtenerTipoUsuario(Long usuario){
        return tipoUsuarioUsuarioRepository.findByUsuario(usuario);
    }

    public TipoUsuarioUsuarioModel guardarTipoUsuarioUsuario(TipoUsuarioUsuarioModel tipo){
        return tipoUsuarioUsuarioRepository.save(tipo);
    }
}
