package com.api.crud.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.models.FacturaModel;
import com.api.crud.repositories.IFacturaRepository;

@Service
public class FacturaService {
    @Autowired
    IFacturaRepository facturaRepository;

    public FacturaModel guardarFactura(FacturaModel factura){
        return facturaRepository.save(factura);
    }
}
