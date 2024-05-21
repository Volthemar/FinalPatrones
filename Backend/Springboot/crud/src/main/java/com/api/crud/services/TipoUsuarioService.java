package com.api.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.api.crud.models.TipoUsuarioModel;
import com.api.crud.repositories.ITipoUsuarioRepository;

@Service
public class TipoUsuarioService {
    @Autowired
    private ITipoUsuarioRepository tipoUsuario;

    public Optional<TipoUsuarioModel> obtenerTipo(Long tipo){
        return tipoUsuario.findById(tipo);
    }
}
