package com.api.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@RestController
public class EnviarCodigo {

    @Autowired
    private JavaMailSender emailSender;

    @PostMapping("/api/auth/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Aqui podria autenticar el usuario

        String verificationCode = generateVerificationCode();

        // Envia el código por correo electrónico
        sendVerificationCodeByEmail(loginRequest.getEmail(), verificationCode);

        return ResponseEntity.ok("Código de verificación enviado por correo electrónico.");
    }

    private String generateVerificationCode() {
        return "123456";
    }

    public void sendVerificationCodeByEmail(String email, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Código de verificación para inicio de sesión");
        message.setText("Tu código de verificación es: " + verificationCode);
        emailSender.send(message);
        System.out
                .println("Enviando código de verificación por correo electrónico a " + email + ": " + verificationCode);
    }

    static class LoginRequest {
        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
