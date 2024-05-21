package com.api.crud.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import com.api.crud.DTO.Request.IpCaptureRequest;
import com.api.crud.models.Ip;
import com.api.crud.repositories.IpRepository;

@Service
public class IpService {

    @Autowired
    private IpRepository ipRepository;

    public void captureIp(IpCaptureRequest request) {
        Ip ip = new Ip();
        ip.setDireccionIp(request.getIpAddress());
         ip.setUsuarioFk(request.getUserId());
         ip.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
         ipRepository.save(ip);
    }
}

