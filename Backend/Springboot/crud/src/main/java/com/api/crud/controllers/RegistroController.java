package com.api.crud.controllers;

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
import com.api.crud.services.UsuarioService;
import com.api.crud.services.models.EmailDTO;

import jakarta.mail.MessagingException;

import java.util.Date;
import java.util.Calendar;

@RestController
@RequestMapping("")
public class RegistroController {
    @Autowired
    private UsuarioService userService;

    @Autowired
    private IEmailService emailService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/registroPersona")
    public Map<String,Object> registroPersona(@RequestBody RegistroPersonaRequest registroPersona) throws MessagingException{
        String nombre = registroPersona.getNombre();
        String identificacion = registroPersona.getIdentificacion();
        String correo = registroPersona.getCorreo();
        String usuario = "Da04vid";
        String contrasena = "1234"; // GENERAR CONTRASEÑA CON  LOS ESTANDARES 
        
        Date fecha = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.HOUR_OF_DAY,-5);
        Date fechaColombia = calendar.getTime();
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setUsuario(usuario); //GENERAR USUARIO
        usuarioModel.setContrasena(contrasena); // ENVIAR CONTRASEÑA ENCRIPTADA
        usuarioModel.setCod_verificacion("");
        usuarioModel.setCorreo(correo);
        usuarioModel.setNombre(nombre);
        usuarioModel.setIdentificacion(identificacion);
        usuarioModel.setNum_intentos(0);
        usuarioModel.setEstado(true);
        usuarioModel.setFecha_creacion(fechaColombia);
        userService.guardarUsuario(usuarioModel);
        EmailDTO email = new EmailDTO();
        email.setAsunto("Confirmación de cuenta");
        email.setDestinatario(correo);
        email.setUsuario(usuario);
        email.setContrasena(contrasena);//ENVIAR CONTRASEÑA SIN ENCRIPTAR
        emailService.enviarCorreoRegistro(email);
        return Map.of("data", usuarioModel, "msg", "Usuario creado con exito");
    }

}
