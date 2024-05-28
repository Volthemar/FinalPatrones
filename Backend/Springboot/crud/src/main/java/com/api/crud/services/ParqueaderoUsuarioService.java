package com.api.crud.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.dto.request.UsuarioRequest;
import com.api.crud.dto.response.ParqueaderoUsuarioResponse;
import com.api.crud.repositories.IParqueaderoUsuarioRepository;

@Service
public class ParqueaderoUsuarioService {
    @Autowired
    private IParqueaderoUsuarioRepository parqueaderoUsuarioRepository;

    public Long obtenerIdParqueadero(Long usuario) {
        return parqueaderoUsuarioRepository.findByUsuario(usuario);
    }

    public Map<String, Object> obtenerInfoParqueaderoUsuario(UsuarioRequest usuarioRequest) {
        ParqueaderoUsuarioResponse parquaderoInfo = new ParqueaderoUsuarioResponse();
        parquaderoInfo.setParqueadero(obtenerIdParqueadero(usuarioRequest.getUsuario_id()));
        parquaderoInfo.setUsuario(usuarioRequest.getUsuario_id());
        return Map.of("data", parquaderoInfo, "msg", "Parqueaderos");
    }
}
