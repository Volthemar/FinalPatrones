package com.api.crud.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.models.CiudadModel;
import com.api.crud.repositories.ICiudadRepository;
import com.api.crud.dto.request.CiudadRequest;

@Service
public class CiudadService {
    @Autowired
    ICiudadRepository ciudadRepository;

    public List<CiudadModel> obtenerCiudades(boolean activo) {
        return ciudadRepository.findByActivo(activo);
    }

    public CiudadModel guardarCiudad(CiudadRequest ciudad) {
        CiudadModel ciudadNueva = new CiudadModel();
        ciudadNueva.setFecha_creacion(ManejarFechas.obtenerFechaActual());
        ciudadNueva.setLatitud(ciudad.getLatitud());
        ciudadNueva.setLongitud(ciudad.getLongitud());
        ciudadNueva.setNombre(ciudad.getNombre());
        ciudadNueva.setActivo(true);
        return ciudadRepository.save(ciudadNueva);
    }
}

