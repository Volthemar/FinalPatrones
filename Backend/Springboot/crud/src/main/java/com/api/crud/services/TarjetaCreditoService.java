package com.api.crud.services;

import java.util.Map;
import java.util.Optional;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.dto.request.TarjetaRequest;
import com.api.crud.models.TarjetaCreditoModel;
import com.api.crud.models.UsuarioModel;
import com.api.crud.repositories.ITarjetaCreditoRepository;
import com.api.crud.services.models.EmailDTO;

import jakarta.mail.MessagingException;

@Service
public class TarjetaCreditoService {
    @Autowired
    ITarjetaCreditoRepository tarjetaCreditoRepository;

    @Autowired
    private UsuarioService userService;

    @Autowired
    private IEmailService emailService;

    public TarjetaCreditoModel guardarTarjetaCredito(TarjetaCreditoModel tarjeta) {
        return tarjetaCreditoRepository.save(tarjeta);
    }

    public Vector<TarjetaCreditoModel> obtenerTarjetas(Long usuario) {
        return tarjetaCreditoRepository.findByUsuario(usuario);
    }

    public Map<String, Object> guardarTarjetaResponse(TarjetaRequest tarjetaRequest) throws MessagingException {
        TarjetaCreditoModel tarjetaCredito = new TarjetaCreditoModel();
        tarjetaCredito.setFecha_creacion(ManejarFechas.obtenerFechaActual());
        tarjetaCredito.setNumero(tarjetaRequest.getNumero());
        tarjetaCredito.setNombre_propietario(tarjetaRequest.getNombre_propietario());
        tarjetaCredito.setUsuario_fk(tarjetaRequest.getUsuario());
        tarjetaCredito.setCvc(tarjetaRequest.getCvc());
        tarjetaCredito.setFecha_vencimiento(tarjetaRequest.getFecha_vencimiento());
        tarjetaCredito.setActivo(true);
        guardarTarjetaCredito(tarjetaCredito);

        Optional<UsuarioModel> usuario = userService.getPorId(tarjetaRequest.getUsuario());
        EmailDTO email = new EmailDTO();
        email.setNumeroTarjeta(tarjetaRequest.getNumero());
        email.setAsunto("Registro de tarjeta de credito");
        email.setDestinatario(usuario.get().getCorreo());
        emailService.enviarCorreoTarjeta(email);

        return Map.of("data", tarjetaCredito, "msg", "Tarjeta agregada");
    }

    public Map<String, Object> obtenerTarjetasResponse(TarjetaRequest tarjetaRequest) {
        Vector<TarjetaCreditoModel> tarjetas = obtenerTarjetas(tarjetaRequest.getUsuario());
        return Map.of("data", tarjetas, "msg", "OK");
    }
}
