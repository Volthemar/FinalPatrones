package com.api.crud.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.crud.DTO.Request.*;
import com.api.crud.services.CupoService;
// import com.api.crud.services.ManejarFechas;



@RestController
@RequestMapping("")
public class CupoController {
    
    @Autowired
    private CupoService cupoService;
    
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/reservarCupo")
    public Map<String, Object>reservarCupo(@RequestBody ReservarCupoRequest request) {
        // boolean isReserved = cupoService.reservarCupo(request.getUsuario_fk(), request.getParqueadero_fk(), request.getVehiculo_fk());
        return Map.of("data","", "msg", "Cupo reservado con exito");
        
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

     @PostMapping("/ocuparCupoOffline")
     public ResponseEntity<?> ocuparCupoOffline(@RequestBody OcuparCupoOfflineRequest request) {
         boolean isOccupied = cupoService.ocuparCupoOffline(request.getParqueaderoId(),request.getVehiculoId(),request.getNombreCliente());
         if (isOccupied) {
             return ResponseEntity.ok("Cupo ocupado con éxito.");
         } else {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay cupos disponibles.");
         }
     }

     @PostMapping("/leave")
     public ResponseEntity<?> leaveCupo(@RequestBody LeaveCupoRequest request) {
         boolean isLeft = cupoService.leaveCupo(request.getCupoId(), request.isOffline());
         if (isLeft) {
             return ResponseEntity.ok("Cupo ahora está disponible.");
         } else {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al liberar el cupo.");
         }
     }

     @PostMapping("/cancelar")
     public ResponseEntity<?> cancelReservation(@RequestBody CancelReservationRequest request) {
         boolean isCancelled = cupoService.cancelReservation(request.getCupoId());
         if (isCancelled) {
             return ResponseEntity.ok("Reserva cancelada con éxito.");
         } else {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo cancelar la reserva.");
         }
     }
}
