package com.api.crud.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.crud.dto.request.TarifaRequest;
import com.api.crud.services.TarifaService;

@RestController
@RequestMapping("")
public class TarifaController {

    @Autowired
    private TarifaService tarifaService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/tarifaParqueadero")
    public Map<String, Object> tarifaParqueadero(@RequestBody TarifaRequest tarifa){
        return tarifaService.obtenerTarifaParqueaderoResponse(tarifa.getParqueadero_fk());
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/tarifaParqueaderoVehiculo")
    public Map<String, Object> tarifaParqueaderoVehiculo(@RequestBody TarifaRequest tarifa){
        return tarifaService.obtenerTarifaParqueaderoVehiculoResponse(tarifa);
    }
}
