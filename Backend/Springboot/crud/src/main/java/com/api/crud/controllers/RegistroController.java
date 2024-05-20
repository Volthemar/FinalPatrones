package com.api.crud.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.crud.DTO.Request.RegistroPersonaRequest;
import com.api.crud.models.UsuarioModel;
import com.api.crud.services.Encriptar;
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

        String contrasena = Encriptar.generarContrasena();

        String contrasenaEncriptada = Encriptar.encriptarContrasena(contrasena);
        
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
}
