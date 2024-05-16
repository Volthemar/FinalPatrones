package com.api.crud.services;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.models.TarjetaCreditoModel;
import com.api.crud.repositories.ITarjetaCreditoRepository;

@Service
public class TarjetaCreditoService {
    @Autowired 
    ITarjetaCreditoRepository tarjetaCreditoRepository;

    public TarjetaCreditoModel guardarTarjetaCredito(TarjetaCreditoModel tarjeta){
        return tarjetaCreditoRepository.save(tarjeta);
    }

    public Vector<TarjetaCreditoModel> obtenerTarjetas(Long usuario){
        return tarjetaCreditoRepository.findByUsuario(usuario);
    }

}
