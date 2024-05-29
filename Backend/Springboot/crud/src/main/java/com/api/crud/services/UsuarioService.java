package com.api.crud.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.models.*;
import com.api.crud.repositories.*;
import com.api.crud.services.models.EmailDTO;
import com.api.crud.dto.request.LoginCodigoRequest;
import com.api.crud.dto.request.LoginRequest;
import com.api.crud.dto.request.RegistroPersonaRequest;
import com.api.crud.dto.response.LoginResponse;
import com.api.crud.dto.response.RegistroResponse;
import com.api.crud.dto.request.IpCaptureRequest;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class UsuarioService {
    @Autowired
    IUsuarioRepository userRepository;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private IpService ipService;

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @Autowired
    private TipoUsuarioUsuarioService tipoUsuarioUsuarioService;

    public ArrayList<UsuarioModel> getUser() {
        return (ArrayList<UsuarioModel>) userRepository.findAll();
    }

    public UsuarioModel guardarUsuario(UsuarioModel usuario) {
        return userRepository.save(usuario);
    }

    public Optional<UsuarioModel> getPorId(Long id) {
        return userRepository.findById(id);
    }

    public Optional<UsuarioModel> login(String usuario, String contrasena) {
        return userRepository.findByUsuarioAndContrasena(usuario, contrasena);
    }

    public Optional<UsuarioModel> buscarUsuario(String usuario) {
        return userRepository.findByUsuario(usuario);
    }

    public List<UsuarioModel> getAllUsuarios() {
        return userRepository.findAll();
    }

    public String codigoUsuario(Long id) {
        return userRepository.findCodigoForId(id);
    }

    @Autowired
    private IUsuarioRepository IUsuarioRepository;

    public Optional<UsuarioModel> actualizarEstado(long id, boolean estado) {
        Optional<UsuarioModel> usuario = IUsuarioRepository.findById(id);
        if (usuario.isPresent()) {
            UsuarioModel usuarioModel = usuario.get();
            usuarioModel.setEstado(estado);
            IUsuarioRepository.save(usuarioModel);
            return Optional.of(usuarioModel);
        } else {
            return Optional.empty();
        }
    }

    public UsuarioModel updateEstado(Long id, Boolean estado) {
        Optional<UsuarioModel> usuarioOpt = IUsuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            UsuarioModel usuario = usuarioOpt.get();
            usuario.setEstado(estado);
            return IUsuarioRepository.save(usuario);
        }
        return null;
    }

    public List<UsuarioModel> getUsuariosPorEstado(boolean estado) {
        return IUsuarioRepository.findByEstado(estado);
    }

    public List<UsuarioModel> getUsuarios() {
        return IUsuarioRepository.findAll();
    }

    public UsuarioModel updateEstadoToNull(long id) {
        UsuarioModel usuario = IUsuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setEstado(null); // Establecer el estado como null
            return IUsuarioRepository.save(usuario);
        }
        return null; // Usuario no encontrado
    }

    public UsuarioModel updateActivo(Long id, Boolean activo) {
        Optional<UsuarioModel> usuarioOpt = IUsuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            UsuarioModel usuario = usuarioOpt.get();
            usuario.setActivo(activo);
            return IUsuarioRepository.save(usuario);
        }
        return null;
    }

    public List<UsuarioModel> getUsuariosPorActivo(boolean activo) {
        return IUsuarioRepository.findByActivo(activo);
    }

    public Map<String, Object> manejarLogin(LoginRequest loginRequest) throws MessagingException {
        String usuario = loginRequest.getUsuario();
        String contrasena = loginRequest.getContrasena();
        Optional<UsuarioModel> usuarioLoggeado = this.buscarUsuario(usuario);

        if (usuarioLoggeado.isPresent()) {
            return procesarUsuarioLoggeado(usuarioLoggeado.get(), contrasena);
        } else {
            return Map.of("data", "", "msg", "Usuario o contraseña incorrecta");
        }
    }

    private Map<String, Object> procesarUsuarioLoggeado(UsuarioModel usuarioLoggeado, String contrasena) throws MessagingException {
        String contrasenaAlmacenada = usuarioLoggeado.getContrasena();

        if (encriptarContrasena(contrasena).equals(contrasenaAlmacenada)) {
            return manejarUsuarioHabilitado(usuarioLoggeado);
        } else {
            return manejarContrasenaIncorrecta(usuarioLoggeado);
        }
    }

    private Map<String, Object> manejarUsuarioHabilitado(UsuarioModel usuarioLoggeado) throws MessagingException {
        if (usuarioLoggeado.isEstado()) {
            usuarioLoggeado.setNum_intentos(0);
            String codigo = Codigos.generarCodigoLogin();
            usuarioLoggeado.setCod_verificacion(codigo);
            guardarUsuario(usuarioLoggeado);
            enviarCorreoVerificacion(usuarioLoggeado.getCorreo(), codigo);
            return Map.of("data", Map.of("estado", usuarioLoggeado.isEstado(), "id", usuarioLoggeado.getId()), "msg", "Usuario habilitado");
        } else {
            return Map.of("data", "", "msg", "Usuario bloqueado");
        }
    }

    private Map<String, Object> manejarContrasenaIncorrecta(UsuarioModel usuarioLoggeado) {
        int intentos = usuarioLoggeado.getNum_intentos() + 1;
        usuarioLoggeado.setNum_intentos(intentos);
        guardarUsuario(usuarioLoggeado);

        if (intentos >= 3) {
            usuarioLoggeado.setEstado(false);
            guardarUsuario(usuarioLoggeado);
            enviarCorreoBloqueo(usuarioLoggeado.getCorreo());
            return Map.of("data", "", "msg", "El usuario ha sido bloqueado por exceso de intentos");
        } else {
            return Map.of("data", "", "msg", "Contraseña incorrecta");
        }
    }

    private void enviarCorreoVerificacion(String destinatario, String codigo) throws MessagingException {
        EmailDTO email = new EmailDTO();
        email.setDestinatario(destinatario);
        email.setMensaje(codigo);
        email.setAsunto("Código de verificación");
        emailService.enviarCorreoCodigo(email);
    }

    private void enviarCorreoBloqueo(String destinatario) {
        EmailDTO email = new EmailDTO();
        email.setDestinatario(destinatario);
        email.setMensaje("Su cuenta ha sido bloqueada, por favor comuniquese con administración");
        email.setAsunto("Bloqueo de cuenta");
        try {
            emailService.enviarCorreoBloqueo(email);
        } catch (MessagingException e) {
            throw new IllegalArgumentException("Error al enviar correo de bloqueo", e);
        }
    }

    private String encriptarContrasena(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(contrasena.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Error encriptando la contra", e);
        }
    }

    public Map<String, Object> manejarLoginCodigo(LoginCodigoRequest loginCodigoRequest, HttpServletRequest request) {
        Long id = loginCodigoRequest.getId();
        String codigo = loginCodigoRequest.getCodigo();
        String codigoUsuario = this.codigoUsuario(id);

        if (codigo.equals(codigoUsuario)) {
            return procesarCodigoCorrecto(id, request);
        } else {
            capturarIp(request, id);
            return Map.of("msg", "Codigo incorrecto");
        }
    }

    private Map<String, Object> procesarCodigoCorrecto(Long id, HttpServletRequest request) {
        Optional<UsuarioModel> clienteOpt = this.getPorId(id);
        if (clienteOpt.isPresent()) {
            UsuarioModel cliente = clienteOpt.get();
            LoginResponse respuestaLogin = construirRespuestaLogin(cliente);
            capturarIp(request, id);
            return Map.of("data", respuestaLogin, "msg", "Codigo correcto");
        } else {
            return Map.of("msg", "Usuario no encontrado");
        }
    }

    private LoginResponse construirRespuestaLogin(UsuarioModel cliente) {
        LoginResponse respuestaLogin = new LoginResponse();
        respuestaLogin.setNombre(cliente.getNombre());
        respuestaLogin.setCorreo(cliente.getCorreo());
        respuestaLogin.setIdentificacion(cliente.getIdentificacion());
        respuestaLogin.setEstado(cliente.isEstado());
        respuestaLogin.setUsuario(cliente.getUsuario());
        respuestaLogin.setTipo(obtenerTiposUsuario(cliente.getId()));
        respuestaLogin.setId(cliente.getId());
        return respuestaLogin;
    }

    private ArrayList<TipoUsuarioModel> obtenerTiposUsuario(Long usuarioId) {
        ArrayList<TipoUsuarioUsuarioModel> rompimiento = tipoUsuarioUsuarioService.obtenerTipoUsuario(usuarioId);
        ArrayList<TipoUsuarioModel> tipos = new ArrayList<>();
        for (TipoUsuarioUsuarioModel tipoUsuarioUsuario : rompimiento) {
            Optional<TipoUsuarioModel> tipoOpt = tipoUsuarioService.obtenerTipo(tipoUsuarioUsuario.getTipo_usuario_fk());
            tipoOpt.ifPresent(tipos::add);
        }
        return tipos;
    }

    private void capturarIp(HttpServletRequest request, Long usuarioId) {
        IpCaptureRequest ipCaptureRequest = new IpCaptureRequest();
        ipCaptureRequest.setIpAddress(request.getRemoteAddr());
        ipCaptureRequest.setUserId(usuarioId);
        ipService.captureIp(ipCaptureRequest);
    }


    public Map<String, Object> registrarUsuario(RegistroPersonaRequest registroPersona) throws MessagingException {
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
        usuarioModel.setActivo(true);
        guardarUsuario(usuarioModel);

        Optional<UsuarioModel> usuarioAgregado = buscarUsuario(usuario);
        TipoUsuarioUsuarioModel tipo = new TipoUsuarioUsuarioModel();
        tipo.setUsuario_fk(usuarioAgregado.get().getId());
        Long idTipoCliente = tipoUsuarioService.obtenerIdCliente();
        tipo.setTipo_usuario_fk(idTipoCliente);
        tipoUsuarioUsuarioService.guardarTipoUsuarioUsuario(tipo);

        EmailDTO email = new EmailDTO();
        email.setAsunto("Confirmación de cuenta");
        email.setDestinatario(correo);
        email.setUsuario(usuario);
        email.setContrasena(contrasena);
        emailService.enviarCorreoRegistro(email);

        RegistroResponse registro = new RegistroResponse();
        registro.setId(usuarioAgregado.get().getId());
        registro.setNombre(nombre);
        registro.setCorreo(correo);
        registro.setEstado(true);
        registro.setTipo(tipoUsuarioService.obtenerTipo(idTipoCliente).get());
        registro.setActivo(true);

        return Map.of("data", registro, "msg", "Usuario creado con exito");
    }
}
