package com.api.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.models.CupoModel;
import com.api.crud.repositories.ICupoRepository;


@Service
public class CupoService {
    
    @Autowired
    private ICupoRepository cupoRepository;

    public CupoModel reservarCupo(CupoModel cupo) {
        return cupoRepository.save(cupo);
    }

    // public boolean occupyCupo(Long cupoId) {
    //     Optional<Cupo> reservedCupo = cupoRepository.findByIdAndStatus(cupoId, "reserved");
    //     if (reservedCupo.isPresent()) {
    //         reservedCupo.get().setStatus("occupied");
    //         cupoRepository.save(reservedCupo.get());
    //         return true;
    //     }
    //     return false;
    // }

    // public boolean walkInOccupyCupo() {
    //     int availableSpots = cupoOfflineRepository.countByStatus("available");
    //     if (availableSpots > 0) {
    //         CupoOffline cupoOffline = new CupoOffline();
    //         cupoOffline.setStatus("occupied");
    //         cupoOfflineRepository.save(cupoOffline);
    //         return true;
    //     }
    //     return false;
    // }

    // public boolean leaveCupo(Long cupoId, boolean isOffline) {
    //     if (isOffline) {
    //         Optional<CupoOffline> cupo = cupoOfflineRepository.findById(cupoId);
    //         if (cupo.isPresent() && cupo.get().getStatus().equals("occupied")) {
    //             cupo.get().setStatus("available");
    //             cupoOfflineRepository.save(cupo.get());
    //             return true;
    //         }
    //     } else {
    //         Optional<Cupo> cupo = cupoRepository.findById(cupoId);
    //         if (cupo.isPresent() && (cupo.get().getStatus().equals("occupied") || cupo.get().getStatus().equals("reserved"))) {
    //             cupo.get().setStatus("available");
    //             cupoRepository.save(cupo.get());
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    // public boolean cancelReservation(Long cupoId) {
    //     Optional<Cupo> reservedCupo = cupoRepository.findByIdAndStatus(cupoId, "reserved");
    //     if (reservedCupo.isPresent()) {
    //         reservedCupo.get().setStatus("available");
    //         cupoRepository.save(reservedCupo.get());
    //         return true;
    //     }
    //     return false;
    // }
}
