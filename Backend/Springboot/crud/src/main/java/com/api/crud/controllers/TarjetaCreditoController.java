package com.api.crud.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.crud.dto.request.TarjetaRequest;
import com.api.crud.services.TarjetaCreditoService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("")
public class TarjetaCreditoController {

    @Autowired
    private TarjetaCreditoService tarjetaCreditoService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/guardarTarjeta")
    public Map<String, Object> guardarTarjeta(@RequestBody TarjetaRequest tarjeta) throws MessagingException {
        return tarjetaCreditoService.guardarTarjetaResponse(tarjeta);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/tarjetaUsuario")
    public Map<String, Object> tarjetaUsuario(@RequestBody TarjetaRequest tarjeta) {
        return tarjetaCreditoService.obtenerTarjetasResponse(tarjeta);
    }
}
