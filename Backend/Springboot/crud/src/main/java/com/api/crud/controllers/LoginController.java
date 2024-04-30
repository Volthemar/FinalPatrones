package com.api.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import com.api.crud.models.UsuarioModel;
import com.api.crud.DTO.LoginRequest;
import com.api.crud.services.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.UUID;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UsuarioService userService;

    @PostMapping("")
    public Map<String,Object> login(@RequestBody LoginRequest loginRequest) {
        String usuario = loginRequest.getUsuario();
        String contrasena = loginRequest.getContrasena();
        if (!this.userService.login(usuario,contrasena).isEmpty()){
            if (this.userService.login(usuario,contrasena).get().isEstado()) {
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
                    return Map.of("data", "", "msg", "El usuario ha sido bloqueado por exceso de intentos");
                }else{
                    return Map.of("data", "", "msg", "Contraseña incorrecta");
                }

            }else{
                return Map.of("data",this.userService.login(usuario,contrasena),"msg","Usuario o contraseña incorrecta");
            }
        }
    }
    // private String generateVerificationCode() {
    //     // Generar un código de verificación aleatorio
    //     return UUID.randomUUID().toString();
    // }

    // private void sendVerificationEmail(String usuario, String verificationCode) {
    //     // Configurar la conexión a la cuenta de correo electrónico
    //     Properties props = new Properties();
    //     props.put("mail.smtp.host", "your-smtp-host");
    //     props.put("mail.smtp.port", "your-smtp-port");
    //     props.put("mail.smtp.auth", "true");

    //     // Enviar el correo electrónico
    //     Session session = Session.getInstance(props, new Authenticator() {
    //         protected PasswordAuthentication getPasswordAuthentication() {
    //             return new PasswordAuthentication("your-email", "your-password");
    //         }
    //     });

    //     try {
    //         Message message = new MimeMessage(session);
    //         message.setFrom(new InternetAddress("your-email"));
    //         message.setRecipient(Message.RecipientType.TO, new InternetAddress(usuario));
    //         message.setSubject("Verificación de cuenta");
    //         message.setText("Por favor, ingrese el siguiente código de verificación: " + verificationCode);

    //         Transport.send(message);
    //     } catch (MessagingException e) {
    //         // Manejar la excepción
    //     }
    // }
}
