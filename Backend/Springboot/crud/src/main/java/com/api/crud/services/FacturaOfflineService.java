package com.api.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.models.FacturaOfflineModel;
import com.api.crud.repositories.IFacturaOfflineRepository;

@Service
public class FacturaOfflineService {
    @Autowired
    IFacturaOfflineRepository facturaOfflineRepository;

    public FacturaOfflineModel guardarFactura(FacturaOfflineModel factura){
        return facturaOfflineRepository.save(factura);
    }

}
