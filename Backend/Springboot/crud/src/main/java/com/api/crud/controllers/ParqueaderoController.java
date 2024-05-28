package com.api.crud.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.api.crud.dto.request.ParqueaderoRequest;
import com.api.crud.dto.response.ParqueaderoEstadisticasResponse;
import com.api.crud.services.ParqueaderoService;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("")
public class ParqueaderoController {

    @Autowired
    private ParqueaderoService parqueaderoService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/parqueaderoCiudad")
    public Map<String, Object> parqueaderoCiudad(@RequestBody ParqueaderoRequest ciudad) {
        return parqueaderoService.parqueaderoCiudad(ciudad);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/parqueaderoCiudadBasico")
    public Map<String, Object> parqueaderoCiudadBasico(@RequestBody ParqueaderoRequest ciudad) {
        return parqueaderoService.parqueaderoCiudadBasico(ciudad);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/obtenerParqueadero")
    public Map<String, Object> obtenerParqueadero(@RequestBody ParqueaderoRequest parqueadero) {
        return parqueaderoService.obtenerParqueadero(parqueadero);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/guardarParqueadero")
    public Map<String, Object> guardarParqueadero(@RequestBody ParqueaderoRequest parqueadero) {
        return parqueaderoService.guardarParqueadero(parqueadero);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{id}/estadisticasParqueadero")
    public ResponseEntity<ParqueaderoEstadisticasResponse> obtenerEstadisticas(@PathVariable("id") long parqueaderoId) {
        Optional<ParqueaderoEstadisticasResponse> response = parqueaderoService.obtenerEstadisticasParqueadero(parqueaderoId);
        if (response.isPresent()) {
            return ResponseEntity.ok(response.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/estadisticasGlobal")
    public ResponseEntity<ParqueaderoEstadisticasResponse> getEstadisticasGlobales() {
        ParqueaderoEstadisticasResponse estadisticas = parqueaderoService.obtenerEstadisticasGlobales();
        return ResponseEntity.ok(estadisticas);
    }
}
