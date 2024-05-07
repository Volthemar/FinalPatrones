package com.api.crud.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.crud.DTO.LoginRequest;
import com.api.crud.models.UsuarioModel;
import com.api.crud.services.IEmailService;
import com.api.crud.services.UsuarioService;
import com.api.crud.services.models.EmailDTO;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UsuarioService userService;

    @Autowired
    private IEmailService emailService;

    @CrossOrigin(origins="http://localhost:5173")
    @PostMapping("")
    public Map<String,Object> login(@RequestBody LoginRequest loginRequest) throws MessagingException{
        String usuario = loginRequest.getUsuario();
        String contrasena = loginRequest.getContrasena();
        EmailDTO email = new EmailDTO();
        Optional<UsuarioModel> usuarioLoggeado = this.userService.login(usuario,contrasena);
        if (!usuarioLoggeado.isEmpty()){
            
            if (usuarioLoggeado.get().isEstado()) {
                usuarioLoggeado.get().setNum_intentos(0);
                userService.guardarUsuario(usuarioLoggeado.get());
                email.setDestinatario(usuarioLoggeado.get().getCorreo());
                email.setMensaje("123456"); // AQUI VA EL CODIGO ALEATORIO
                email.setAsunto("Código de verificación");
                emailService.enviarCorreo(email);
                return Map.of("data",this.userService.login(usuario,contrasena),"msg","Usuario habilitado");
            }else{
                
                return Map.of("data", "", "msg", "Usuario bloqueado");
            }
        }else{
            Optional<UsuarioModel> usuarioExiste = this.userService.buscarUsuario(usuario);
            if(!usuarioExiste.isEmpty()){
                int intentos = usuarioExiste.get().getNum_intentos();
                intentos+=1;
                usuarioExiste.get().setNum_intentos(intentos);
                userService.guardarUsuario(usuarioExiste.get());
                if (intentos >=3){
                    usuarioExiste.get().setEstado(false);
                    userService.guardarUsuario(usuarioExiste.get());
                    email.setDestinatario(usuarioLoggeado.get().getCorreo());
                    email.setMensaje("Su cuenta ha sido bloqueada, por favor comuniquese con administración");
                    email.setAsunto("Bloqueo de cuenta");
                    emailService.enviarCorreo(email);
                    return Map.of("data", "", "msg", "El usuario ha sido bloqueado por exceso de intentos");
                }else{
                    return Map.of("data", "", "msg", "Contraseña incorrecta");
                }

            }else{
                return Map.of("data",this.userService.login(usuario,contrasena),"msg","Usuario o contraseña incorrecta");
            }
        }
    }

}
