package com.api.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.api.crud.DTO.*;
import com.api.crud.services.CupoService;
import java.util.Optional;

@RestController
@RequestMapping("/api/cupos")
public class CupoController {
    
    @Autowired
    private CupoService cupoService;

    // @PostMapping("/reserve")
    // public ResponseEntity<?> reserveCupo(@RequestBody ReserveCupoRequest request) {
    //     boolean isReserved = cupoService.reserveCupo(request.getUserId());
    //     if (isReserved) {
    //         return ResponseEntity.ok("Cupo reservado con éxito.");
    //     } else {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay cupos disponibles.");
    //     }
    // }

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
