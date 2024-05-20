package com.api.crud.services;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.models.TarifaModel;
import com.api.crud.repositories.ITarifaRepository;

@Service
public class TarifaService {
    @Autowired
    ITarifaRepository tarifaRepository;

    public Vector<TarifaModel> obtenerTarifaParqueadero(Long parqueadero){
        return tarifaRepository.findByParqueadero(parqueadero);
    }

    public Vector<TarifaModel> obtenerTarifaParqueaderoVehiculo(Long parqueadero,Long vehiculo){
        return tarifaRepository.findByParqueaderoAndVehiculo(parqueadero,vehiculo);
    }
}
