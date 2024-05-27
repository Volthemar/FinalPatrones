package com.api.crud.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.crud.DTO.Request.*;
import com.api.crud.models.CupoModel;
import com.api.crud.services.Codigos;
import com.api.crud.services.CupoService;
import com.api.crud.services.IEmailService;
// import com.api.crud.services.ManejarFechas;
import com.api.crud.services.ManejarFechas;
import com.api.crud.services.UsuarioService;
import com.api.crud.services.models.EmailCupo;

import jakarta.mail.MessagingException;



@RestController
@RequestMapping("")
public class CupoController {
    
    @Autowired
    private CupoService cupoService;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private UsuarioService usuarioService;
    
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/reservarCupo")
    public Map<String, Object> reservarCupo(@RequestBody ReservarCupoRequest request) throws MessagingException{
        boolean disponibilidad = cupoService.verificarDisponibilidadCupo(request.getParqueaderoId(),request.getVehiculoId(),request.getHora_llegada());
        if (disponibilidad){
            CupoModel cupo = new CupoModel();
            cupo.setEstado(CupoModel.Estado.RESERVADO);
            cupo.setUsuario_fk(request.getUsuarioId());
            cupo.setParqueadero_fk(request.getParqueaderoId());
            cupo.setVehiculo_fk(request.getVehiculoId());
            cupo.setFecha_creacion(ManejarFechas.obtenerFechaActual());
            cupo.setHora_llegada(request.getHora_llegada());
            cupo.setHoras_pedidas(request.getHoras());
            cupo.setPagado(false);
            cupo.setActivo(true);
            String codigo = Codigos.generarCodigoCupo();
            cupo.setCodigo(codigo);
            cupoService.guardarCupo(cupo);
            EmailCupo emailCupo = new EmailCupo();
            emailCupo.setAsunto("Confirmación de Reserva de Parqueadero y Código de Acceso");
            emailCupo.setDestinatario(usuarioService.getPorId(request.getUsuarioId()).get().getCorreo());
            emailCupo.setCodigo(codigo);
            emailCupo.setHoraLlegada(request.getHora_llegada());
            emailCupo.setHorasSolicitadas(request.getHoras());
            emailService.enviarCorreoCodigoCupo(emailCupo);
            return Map.of("data",Map.of("codigo",codigo), "msg", "Cupo reservado con exito");
        }
        return Map.of("data","", "msg", "Sin disponibilidad"); 
    }

    @PostMapping("/ocuparCupo")
    public ResponseEntity<?> occuparCupo(@RequestBody OccupyCupoRequest request) {
        boolean isOccupied = cupoService.occupyCupo(request.getCupoId());
        if (isOccupied) {
            return ResponseEntity.ok("Cupo ocupado con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo ocupar el cupo.");
        }
    }

    // @PostMapping("/leave")
    // public ResponseEntity<?> leaveCupo(@RequestBody LeaveCupoRequest request) {
    //     boolean isLeft = cupoService.leaveCupo(request.getCupoId(), request.isOffline());
    //     if (isLeft) {
    //         return ResponseEntity.ok("Cupo ahora está disponible.");
    //     } else {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al liberar el cupo.");
    //     }
    // }

    // @PostMapping("/cancelar")
    // public ResponseEntity<?> cancelReservation(@RequestBody CancelReservationRequest request) {
    //     boolean isCancelled = cupoService.cancelReservation(request.getCupoId());
    //     if (isCancelled) {
    //         return ResponseEntity.ok("Reserva cancelada con éxito.");
    //     } else {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo cancelar la reserva.");
    //     }
    // }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/verificarDisponibilidad")
    public Map<String, Object>verificarDisponibilidad(@RequestBody VerificarDisponibilidadRequest verificar) {
        boolean cupoDisponible = cupoService.verificarDisponibilidadCupo(verificar.getParqueaderoId(), verificar.getVehiculoId(),verificar.getHora_llegada());
        return Map.of("data",cupoDisponible, "msg", "Disponibilidad");
        
    }

}
