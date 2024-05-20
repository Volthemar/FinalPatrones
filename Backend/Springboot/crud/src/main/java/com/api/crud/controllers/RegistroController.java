package com.api.crud.controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.crud.DTO.RegistroPersonaRequest;
import com.api.crud.models.UsuarioModel;
import com.api.crud.services.IEmailService;
import com.api.crud.services.ManejarFechas;
import com.api.crud.services.UsuarioService;
import com.api.crud.services.models.EmailDTO;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("")
public class RegistroController {
    @Autowired
    private UsuarioService userService;

    @Autowired
    private IEmailService emailService;

    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String ALL_CHARS = LOWERCASE_CHARS + UPPERCASE_CHARS + DIGITS;
    private static final SecureRandom RANDOM = new SecureRandom();

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/registroPersona")
    public Map<String, Object> registroPersona(@RequestBody RegistroPersonaRequest registroPersona)
            throws MessagingException {
        String nombre = registroPersona.getNombre();
        String identificacion = registroPersona.getIdentificacion();
        String correo = registroPersona.getCorreo();
        String nombreAbreviado = nombre.substring(0, 3).toUpperCase();

        String ultimosDigitosIdentificacion = identificacion.substring(identificacion.length() - 2);
        String[] partesNombre = nombre.split(" ");
        String apellido = partesNombre[1];
        String abreviaturaApellido = apellido.substring(0, 3).toUpperCase();
        String usuario = nombreAbreviado + ultimosDigitosIdentificacion + abreviaturaApellido;

        String contrasena = generatePassword();

        String contrasenaEncriptada = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(contrasena.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            contrasenaEncriptada = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setUsuario(usuario);
        usuarioModel.setContrasena(contrasenaEncriptada);
        usuarioModel.setCod_verificacion("");
        usuarioModel.setCorreo(correo);
        usuarioModel.setNombre(nombre);
        usuarioModel.setIdentificacion(identificacion);
        usuarioModel.setNum_intentos(0);
        usuarioModel.setEstado(true);
        usuarioModel.setFecha_creacion(ManejarFechas.obtenerFechaActual());
        userService.guardarUsuario(usuarioModel);

        EmailDTO email = new EmailDTO();
        email.setAsunto("Confirmación de cuenta");
        email.setDestinatario(correo);
        email.setUsuario(usuario);
        email.setContrasena(contrasena);
        emailService.enviarCorreoRegistro(email);

        return Map.of("data", usuarioModel, "msg", "Usuario creado con exito");
    }

    private String generatePassword() {
        StringBuilder password = new StringBuilder();

        password.append(LOWERCASE_CHARS.charAt(RANDOM.nextInt(LOWERCASE_CHARS.length())));
        password.append(UPPERCASE_CHARS.charAt(RANDOM.nextInt(UPPERCASE_CHARS.length())));
        password.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));

        for (int i = 0; i < 2; i++) {
            password.append(ALL_CHARS.charAt(RANDOM.nextInt(ALL_CHARS.length())));
        }

        int remainingLength = 8 - password.length();
        for (int i = 0; i < remainingLength; i++) {
            char randomChar = ALL_CHARS.charAt(RANDOM.nextInt(ALL_CHARS.length()));
            password.insert(RANDOM.nextInt(password.length() + 1), randomChar);
        }

        return password.toString();
    }
}
