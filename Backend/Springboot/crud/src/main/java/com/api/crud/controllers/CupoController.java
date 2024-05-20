package com.api.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.crud.DTO.*;
import com.api.crud.DTO.Request.ReservarCupoRequest;
import com.api.crud.models.CupoModel;
import com.api.crud.services.CupoService;
import com.api.crud.services.ManejarFechas;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("")
public class CupoController {
    
    @Autowired
    private CupoService cupoService;
    
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/reservarCupo")
    public Map<String,Object> reserveCupo(@RequestBody ReservarCupoRequest cupo) {
        CupoModel cupoNuevo = new CupoModel();
        cupoNuevo.setUsuario_fk(cupo.getUsuario_fk());
        cupoNuevo.setVehiculo_fk(cupo.getVehiculo_fk());
        cupoNuevo.setParqueadero_fk(cupo.getParqueadero_fk());
        cupoNuevo.setHora_llegada(cupo.getHora_llegada());
        cupoNuevo.setHoras_pedidas(cupo.getHoras_pedidas());
        cupoNuevo.setFecha_creacion(ManejarFechas.obtenerFechaActual());
        cupoNuevo.setPagado(true);
        cupoService.reservarCupo(cupoNuevo);
        return Map.of("data", cupoNuevo, "msg", "cupo agregado");
    }

    // @PostMapping("/occupy")
    // public ResponseEntity<?> occupyCupo(@RequestBody OccupyCupoRequest request) {
    //     boolean isOccupied = cupoService.occupyCupo(request.getCupoId());
    //     if (isOccupied) {
    //         return ResponseEntity.ok("Cupo ocupado con éxito.");
    //     } else {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo ocupar el cupo.");
    //     }
    // }

    // @PostMapping("/walkin")
    // public ResponseEntity<?> walkInOccupyCupo() {
    //     boolean isOccupied = cupoService.walkInOccupyCupo();
    //     if (isOccupied) {
    //         return ResponseEntity.ok("Cupo ocupado con éxito.");
    //     } else {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay cupos disponibles.");
    //     }
    // }

    // @PostMapping("/leave")
    // public ResponseEntity<?> leaveCupo(@RequestBody LeaveCupoRequest request) {
    //     boolean isLeft = cupoService.leaveCupo(request.getCupoId(), request.isOffline());
    //     if (isLeft) {
    //         return ResponseEntity.ok("Cupo ahora está disponible.");
    //     } else {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al liberar el cupo.");
    //     }
    // }

    // @PostMapping("/cancel")
    // public ResponseEntity<?> cancelReservation(@RequestBody CancelReservationRequest request) {
    //     boolean isCancelled = cupoService.cancelReservation(request.getCupoId());
    //     if (isCancelled) {
    //         return ResponseEntity.ok("Reserva cancelada con éxito.");
    //     } else {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo cancelar la reserva.");
    //     }
    // }
}
