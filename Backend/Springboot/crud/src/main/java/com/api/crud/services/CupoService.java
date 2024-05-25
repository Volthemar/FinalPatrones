package com.api.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.models.CupoOfflineModel;
import com.api.crud.models.ParqueaderoModel;
import com.api.crud.models.CupoModel;
import com.api.crud.repositories.ICupoRepository;
import com.api.crud.repositories.IParqueaderoRepository;
import com.api.crud.repositories.ICupoOfflineRepository;

import java.util.Optional;
import java.sql.Timestamp;

@Service
public class CupoService {
    
    @Autowired
    private ICupoRepository cupoRepository;

    @Autowired
    private ICupoOfflineRepository cupoOfflineRepository;

    @Autowired
    private IParqueaderoRepository parqueaderoRepository;

    public boolean reservarCupo(Long userId, Long parqueaderoId, Long vehiculoId) {
        Optional<ParqueaderoModel> parqueaderoOptional = parqueaderoRepository.findById(parqueaderoId);
        if (parqueaderoOptional.isPresent()) {
            ParqueaderoModel parqueadero = parqueaderoOptional.get();
            if (canReserveSpot(parqueadero.getId(), vehiculoId)) {
                CupoModel cupo = new CupoModel();
                cupo.setEstado(CupoModel.Estado.RESERVADO);
                cupo.setUsuario_fk(userId);
                cupo.setParqueadero_fk(parqueaderoId);
                cupo.setVehiculo_fk(vehiculoId);
                cupoRepository.save(cupo);

                updateOccupiedSpots(parqueadero.getId(), vehiculoId);
                parqueaderoRepository.save(parqueadero);

                return true;
            }
        }
        return false;
    }

    public boolean occupyCupo(Long cupoId) {
         Optional<CupoModel> reservedCupo = cupoRepository.findByIdAndEstado(cupoId, CupoModel.Estado.RESERVADO);
         if (reservedCupo.isPresent()) {
             reservedCupo.get().setEstado(CupoModel.Estado.OCUPADO);
             cupoRepository.save(reservedCupo.get());
             return true;
         }
         return false;
     }

    public boolean ocuparCupoOffline(Long parqueaderoId, Long vehiculoId, String NombreCliente) {
        Optional<ParqueaderoModel> parqueaderoOptional = parqueaderoRepository.findById(parqueaderoId);
        if (parqueaderoOptional.isPresent()) {
            ParqueaderoModel parqueadero = parqueaderoOptional.get();
            if (canReserveSpot(parqueadero.getId(), vehiculoId)) {
                CupoOfflineModel cupoOffline = new CupoOfflineModel();
                cupoOffline.setParqueadero_fk(parqueaderoId);
                cupoOffline.setVehiculo_fk(vehiculoId);
                cupoOffline.setNombre_Cliente(NombreCliente);
                cupoOffline.setHora_llegada(new Timestamp(System.currentTimeMillis()));
                cupoOfflineRepository.save(cupoOffline);

                updateOccupiedSpots(parqueadero.getId(), vehiculoId);
                parqueaderoRepository.save(parqueadero);

                return true;
            }
        }
        return false;
    }


     public boolean leaveCupo(Long cupoId, boolean isOffline) {
         if (isOffline) {
             Optional<CupoOfflineModel> cupo = cupoOfflineRepository.findById(cupoId);
             if (cupo.isPresent()) {
                 cupoOfflineRepository.delete(cupo.get());
                 return true;
             }
         } else {
             Optional<CupoModel> cupo = cupoRepository.findById(cupoId);
             if (cupo.isPresent() && (cupo.get().getEstado().equals(CupoModel.Estado.OCUPADO))) {
                 cupoRepository.delete(cupo.get());
                 return true;
             }
         }
         return false;
     }

     public boolean cancelReservation(Long cupoId) {
         Optional<CupoModel> reservedCupo = cupoRepository.findByIdAndEstado(cupoId, CupoModel.Estado.RESERVADO);
         if (reservedCupo.isPresent()) {
             cupoRepository.delete(reservedCupo.get());
             return true;
         }
         return false;
     }

     private boolean canReserveSpot(long parqueaderoId, long vehicleId) {
        Optional<ParqueaderoModel> optionalParqueadero = parqueaderoRepository.findById(parqueaderoId);
        if (!optionalParqueadero.isPresent()) {
            return false;
        }
        ParqueaderoModel parqueadero = optionalParqueadero.get();
        int totalSpots = 0;
        int occupiedSpots = 0;

        String tipoVehiculo = cupoRepository.findVehicleTypeById(vehicleId);

        switch (tipoVehiculo.toUpperCase()) {
            case "CARRO":
                totalSpots = parqueadero.getCupo_carro_total();
                occupiedSpots = cupoRepository.countByParqueaderoIdAndVehiculoTipo(parqueaderoId, "CARRO") +
                                cupoOfflineRepository.countByParqueaderoIdAndVehiculoTipo(parqueaderoId, "CARRO");
                break;
            case "MOTO":
                totalSpots = parqueadero.getCupo_moto_total();
                occupiedSpots = cupoRepository.countByParqueaderoIdAndVehiculoTipo(parqueaderoId, "MOTO") +
                                cupoOfflineRepository.countByParqueaderoIdAndVehiculoTipo(parqueaderoId, "MOTO");
                break;
            case "BICI":
                totalSpots = parqueadero.getCupo_bici_total();
                occupiedSpots = cupoRepository.countByParqueaderoIdAndVehiculoTipo(parqueaderoId, "BICI") +
                                cupoOfflineRepository.countByParqueaderoIdAndVehiculoTipo(parqueaderoId, "BICI");
                break;
            default:
                return false;
        }

        return occupiedSpots < totalSpots;
    }

    public void updateOccupiedSpots(long parqueaderoId, long vehicleId) {
        Optional<ParqueaderoModel> optionalParqueadero = parqueaderoRepository.findById(parqueaderoId);
        if (!optionalParqueadero.isPresent()) {
            return;
        }
        int occupiedSpots = 0;
        ParqueaderoModel parqueadero = optionalParqueadero.get();

        String tipoVehiculo = cupoRepository.findVehicleTypeById(vehicleId);

        switch (tipoVehiculo.toUpperCase()) {
            case "CARRO":
                occupiedSpots = cupoRepository.countByParqueaderoIdAndVehiculoTipo(parqueaderoId, "CARRO") +
                                cupoOfflineRepository.countByParqueaderoIdAndVehiculoTipo(parqueaderoId, "CARRO");
                parqueadero.setCupo_uti_carro(occupiedSpots);     
                break;
            case "MOTO":
                occupiedSpots = cupoRepository.countByParqueaderoIdAndVehiculoTipo(parqueaderoId, "MOTO") +
                                cupoOfflineRepository.countByParqueaderoIdAndVehiculoTipo(parqueaderoId, "MOTO");
                parqueadero.setCupo_uti_moto(occupiedSpots);                     
                break;
            case "BICI":
                occupiedSpots = cupoRepository.countByParqueaderoIdAndVehiculoTipo(parqueaderoId, "BICI") +
                                cupoOfflineRepository.countByParqueaderoIdAndVehiculoTipo(parqueaderoId, "BICI");
                parqueadero.setCupo_uti_bici(occupiedSpots);                
                break;
        }
        parqueaderoRepository.save(parqueadero);
    }
}

