package com.api.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.crud.services.IpService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/navigation")
public class NavigationController {

    @Autowired
    private IpService ipService;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/track")
    public Map<String, Object> trackNavigation(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        ipService.saveIpAddress(ipAddress, null); // Save IP without a user ID since this is a visitor

        return Map.of("data", "IP logged", "ipAddress", ipAddress);
    }
}
