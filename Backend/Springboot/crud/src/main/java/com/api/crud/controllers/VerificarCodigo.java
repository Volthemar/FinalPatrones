package com.api.crud.controllers;

import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class VerificarCodigo {

    // Mapa para almacenar los códigos de verificación
    private Map<String, String> verificationCodes = new HashMap<>();

    // Método para almacenar un código de verificación asociado a un correo
    // electrónico
    public void storeVerificationCode(String email, String code) {
        verificationCodes.put(email, code);
    }

    // Método para verificar si un código de verificación es correcto para un correo
    // electrónico dado
    public boolean verifyCode(String email, String code) {
        // Obtener el código de verificación asociado al correo electrónico
        String storedCode = verificationCodes.get(email);
        // Revisa que coincidan
        return storedCode != null && storedCode.equals(code);
    }
}
