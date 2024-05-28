package com.api.crud.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.api.crud.dto.request.ReservarCupoOfflineRequest;
import com.api.crud.services.CupoService;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("")
public class CupoOfflineControler {
    @Autowired
    private CupoService cupoService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/reservarCupoOffline")
    public Map<String, Object> reservarCupoOffline(@RequestBody ReservarCupoOfflineRequest request) {
        return cupoService.reservarCupoOffline(request);
    }
    
}
