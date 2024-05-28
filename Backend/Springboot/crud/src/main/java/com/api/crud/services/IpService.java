package com.api.crud.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import com.api.crud.dto.request.IpCaptureRequest;
import com.api.crud.models.IpModel;
import com.api.crud.repositories.IpRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class IpService {

    @Autowired
    private IpRepository ipRepository;

    public void captureIp(IpCaptureRequest request) {
        IpModel ip = new IpModel();
        ip.setActivo(true);
        ip.setDireccionIp(request.getIpAddress());
        ip.setUsuarioFk(request.getUserId());
        ip.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
        ipRepository.save(ip);
    }

    public void captureIpFromRequest(HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        IpCaptureRequest ipCaptureRequest = new IpCaptureRequest();
        ipCaptureRequest.setIpAddress(clientIp);
        captureIp(ipCaptureRequest);
    }
}

