package com.api.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.api.crud.services.IpService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("")
public class NavigationController {

    @Autowired
    private IpService ipService;

    @GetMapping("/track")
    public void trackVisitor(HttpServletRequest request) {
        ipService.captureIpFromRequest(request);
    }
}
