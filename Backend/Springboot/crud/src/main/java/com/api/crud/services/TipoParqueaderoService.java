package com.api.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.api.crud.models.TipoParqueaderoModel;
import com.api.crud.repositories.ITipoParqueaderoRepository;

@Service
public class TipoParqueaderoService {
    @Autowired
    ITipoParqueaderoRepository tipoParqueaderoRepository;

    public String obtenerTipo(Long tipo_id) {
        String tipo = tipoParqueaderoRepository.findById(tipo_id).get().getTipo();
        return tipo;
    }

    public List<TipoParqueaderoModel> tomarTodo(Boolean activo) {
        return tipoParqueaderoRepository.findByActivo(activo);
    }
}
