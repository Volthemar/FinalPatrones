package com.api.crud.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.api.crud.dto.request.*;
import com.api.crud.services.CupoService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("")
public class CupoController {
    
    @Autowired
    private CupoService cupoService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/reservarCupo")
    public Map<String, Object> reservarCupo(@RequestBody ReservarCupoRequest request) throws MessagingException {
        return cupoService.reservarCupo(request);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/ocuparCupo")
    public Map<String, Object> ocuparCupo(@RequestBody OcuparRequest request) throws MessagingException {
        return cupoService.ocuparCupo(request);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/finalizarCupoOnline")
    public Map<String, Object> finalizarCupoOn(@RequestBody OcuparRequest request) {
        return cupoService.finalizarCupoOn(request);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/finalizarCupoOffline")
    public Map<String, Object> finalizarCupoOff(@RequestBody OcuparRequest request) {
        return cupoService.finalizarCupoOff(request);
    }

    @PostMapping("/cancelarCupo")
    public Map<String, Object> cancelarCupo(@RequestBody CancelReservationRequest request) {
        return cupoService.cancelarCupo(request);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/verificarDisponibilidad")
    public Map<String, Object> verificarDisponibilidad(@RequestBody VerificarDisponibilidadRequest verificar) {
        return cupoService.verificarDisponibilidad(verificar);
    }
}
