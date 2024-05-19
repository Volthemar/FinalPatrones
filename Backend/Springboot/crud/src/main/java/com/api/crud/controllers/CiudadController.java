package com.api.crud.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.crud.services.CiudadService;

@RestController
@RequestMapping("")
public class CiudadController {
    @Autowired
    private CiudadService ciudadService;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/obtenerCiudades")
    public Map<String,Object> obenerCiudades(){
        return Map.of("data", ciudadService.obtenerCiudades(), "msg", "Ciudades");
    }
}
