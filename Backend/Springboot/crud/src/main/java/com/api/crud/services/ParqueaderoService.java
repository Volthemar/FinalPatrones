package com.api.crud.services;

import java.util.Vector;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.models.ParqueaderoModel;
import com.api.crud.repositories.IParqueaderoRepository;

@Service
public class ParqueaderoService {
    @Autowired
    IParqueaderoRepository parqueaderoRepository;

    public Vector<ParqueaderoModel> obtenerParqueaderoCiudad(Long ciudad){
        return parqueaderoRepository.findByCiudad(ciudad);
    }

    public Optional<ParqueaderoModel> obtenerParqueadero(Long parqueadero){
        return parqueaderoRepository.findById(parqueadero);
    }

    public ParqueaderoModel guardarParqueadero(ParqueaderoModel parqueadero){
        return parqueaderoRepository.save(parqueadero);
    }
}
