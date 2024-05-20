package com.api.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.api.crud.models.VehiculoModel;
import com.api.crud.repositories.IVehiculoRepository;

@Service
public class VehiculoService {
    @Autowired
    IVehiculoRepository vehiculoRepository;

    public List<VehiculoModel> obtenerVehiculos(){
        return vehiculoRepository.findAll();
    }
}
