package com.api.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.models.CupoOfflineModel;
import com.api.crud.models.ParqueaderoModel;
import com.api.crud.models.CupoModel;
import com.api.crud.repositories.ICupoRepository;
import com.api.crud.repositories.IParqueaderoRepository;
import com.api.crud.repositories.ICupoOfflineRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CupoService {
    
    @Autowired
    private ICupoRepository cupoRepository;

    @Autowired
    private ICupoOfflineRepository cupoOfflineRepository;

    @Autowired
    private IParqueaderoRepository parqueaderoRepository;

    public boolean reservarCupo(Long parqueaderoId, Long usuarioId, Long vehiculoId, int horas, Date hora_llegada, Long tarjetaId) {
        Optional<ParqueaderoModel> parqueaderoOptional = parqueaderoRepository.findById(parqueaderoId);
        if (parqueaderoOptional.isPresent()) {
            if (verificarDisponibilidadCupo(parqueaderoId, vehiculoId, hora_llegada)) {
                return true;
            }
        }
        return false;
    }

    public CupoModel guardarCupo(CupoModel cupo){
        return cupoRepository.save(cupo);
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

    // public boolean ocuparCupoOffline(Long parqueaderoId, Long vehiculoId, String NombreCliente) {
    //     Optional<ParqueaderoModel> parqueaderoOptional = parqueaderoRepository.findById(parqueaderoId);
    //     if (parqueaderoOptional.isPresent()) {
    //         ParqueaderoModel parqueadero = parqueaderoOptional.get();
    //         if (verificarEspacio(parqueadero, vehiculoId)) {
    //             CupoOfflineModel cupoOffline = new CupoOfflineModel();
    //             cupoOffline.setParqueadero_fk(parqueaderoId);
    //             cupoOffline.setVehiculo_fk(vehiculoId);
    //             cupoOffline.setNombre_Cliente(NombreCliente);
    //             cupoOffline.setHora_llegada(new Timestamp(System.currentTimeMillis()));
    //             cupoOfflineRepository.save(cupoOffline);

    //             actualizarCupo(parqueadero, vehiculoId);
    //             parqueaderoRepository.save(parqueadero);

    //             return true;
    //         }
    //     }
    //     return false;
    // }


    //  public boolean leaveCupo(Long cupoId, boolean isOffline) {
    //      if (isOffline) {
    //          Optional<CupoOfflineModel> cupo = cupoOfflineRepository.findById(cupoId);
    //          if (cupo.isPresent()) {
    //              cupoOfflineRepository.delete(cupo.get());
    //              return true;
    //          }
    //      } else {
    //          Optional<CupoModel> cupo = cupoRepository.findById(cupoId);
    //          if (cupo.isPresent() && (cupo.get().getEstado().equals(CupoModel.Estado.OCUPADO))) {
    //              cupoRepository.delete(cupo.get());
    //              return true;
    //          }
    //      }
    //      return false;
    //  }

    //  public boolean cancelReservation(Long cupoId) {
    //      Optional<CupoModel> reservedCupo = cupoRepository.findByIdAndEstado(cupoId, CupoModel.Estado.RESERVADO);
    //      if (reservedCupo.isPresent()) {
    //          cupoRepository.delete(reservedCupo.get());
    //          return true;
    //      }
    //      return false;
    //  }

    public boolean verificarDisponibilidadCupo(Long parqueaderoId, Long vehiculoId, Date horaLlegada){

        List<CupoModel> cuposReservados = cupoRepository.findByParqueaderoAndVehiculoReservado(parqueaderoId, vehiculoId);
        Date horaLlegadaEvaluar = new Date();
        Date horaSalidaEvaluar = new Date();
        int horasPedidas = 0;
        int cuposUtilizados = 0;
        for (int i=0;i<cuposReservados.size();i++){
            horaLlegadaEvaluar = cuposReservados.get(i).getHora_llegada();
            horasPedidas = cuposReservados.get(i).getHoras_pedidas();
            horaSalidaEvaluar = ManejarFechas.sumarHoras(horaLlegadaEvaluar,horasPedidas);
            if (horaLlegada.before(horaSalidaEvaluar) || horaLlegada.after(horaLlegadaEvaluar) || horaLlegada.equals(horaLlegadaEvaluar)){
                cuposUtilizados+=1;
            }
        }
        cuposUtilizados = cuposUtilizados + cupoRepository.findByParqueaderoAndVehiculoOcupado(parqueaderoId, vehiculoId).size();
        cuposUtilizados = cuposUtilizados + cupoOfflineRepository.findByParqueaderoAndVehiculoOcupado(parqueaderoId, vehiculoId).size();

        Optional<ParqueaderoModel> parqueadero = parqueaderoRepository.findById(parqueaderoId);
        String tipoVehiculo = cupoRepository.findVehicleTypeById(vehiculoId);
        int cupoTotal = 0;

        switch (tipoVehiculo.toUpperCase()) {
            case "CARRO":
                cupoTotal = parqueadero.get().getCupo_carro_total();
                break;
            case "MOTO":
                cupoTotal = parqueadero.get().getCupo_moto_total();
                break;
            case "BICICLETA":
                cupoTotal = parqueadero.get().getCupo_bici_total();
                break;
        }
        if (cuposUtilizados < cupoTotal){
            return true;
        }else{
            return false;
        }
        
    }

}

