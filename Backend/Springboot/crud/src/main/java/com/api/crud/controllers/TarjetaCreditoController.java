package com.api.crud.controllers;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.crud.DTO.TarjetaRequest;
import com.api.crud.models.TarjetaCreditoModel;
import com.api.crud.services.IEmailService;
import com.api.crud.services.TarjetaCreditoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Vector;

@RestController
@RequestMapping("")
public class TarjetaCreditoController {
    @Autowired
    private TarjetaCreditoService tarjetaCreditoService;

    @Autowired
    private IEmailService emailService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/guardarTarjeta")
    public Map<String, Object> guardarTarjeta(@RequestBody TarjetaRequest tarjeta){
        TarjetaCreditoModel tarjetaCredito = new TarjetaCreditoModel();
        Date fecha = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.HOUR_OF_DAY,-5);
        Date fechaColombia = calendar.getTime();
        tarjetaCredito.setFecha_creacion(fechaColombia);
        tarjetaCredito.setNumero(tarjeta.getNumero());
        tarjetaCredito.setNombre_propietario(tarjeta.getNombre_propietario());
        tarjetaCredito.setUsuario_fk(tarjeta.getUsuario());
        tarjetaCredito.setCvc(tarjeta.getCvc());
        tarjetaCredito.setFecha_vencimiento(tarjeta.getFecha_vencimiento());
        tarjetaCreditoService.guardarTarjetaCredito(tarjetaCredito);
        return Map.of("data", tarjetaCredito, "msg", "Tarjeta agregada");
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/tarjetaUsuario")
    public Map<String, Object> tarjetaUsuario(@RequestBody TarjetaRequest tarjeta) {
        Vector<TarjetaCreditoModel> tarjetas = tarjetaCreditoService.obtenerTarjetas(tarjeta.getUsuario());
        return Map.of("data", tarjetas, "msg", "OK");
    }
    

}
