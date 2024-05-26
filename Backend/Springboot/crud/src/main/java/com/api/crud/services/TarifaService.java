package com.api.crud.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.models.TarifaModel;
import com.api.crud.repositories.ITarifaRepository;

@Service
public class TarifaService {
    @Autowired
    ITarifaRepository tarifaRepository;

    public Optional<TarifaModel> obtenerTarifaParqueadero(Long parqueadero){
        return tarifaRepository.findByParqueadero(parqueadero);
    }

    public Optional<TarifaModel> obtenerTarifaParqueaderoVehiculo(Long parqueadero,Long vehiculo){
        return tarifaRepository.findByParqueaderoAndVehiculo(parqueadero,vehiculo);
    }
}
