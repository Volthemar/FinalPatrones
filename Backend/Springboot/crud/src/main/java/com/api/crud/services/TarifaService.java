package com.api.crud.services;

import java.util.Map;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.dto.request.TarifaRequest;
import com.api.crud.models.TarifaModel;
import com.api.crud.repositories.ITarifaRepository;

@Service
public class TarifaService {
    @Autowired
    ITarifaRepository tarifaRepository;

    public List<TarifaModel> obtenerTarifasPorParqueadero(Long parqueadero) {
        return tarifaRepository.findByParqueadero(parqueadero);
    }

    public Map<String, Object> obtenerTarifaParqueaderoResponse(Long parqueaderoId) {
        List<TarifaModel> tarifas = obtenerTarifasPorParqueadero(parqueaderoId);
        return Map.of("data", tarifas, "msg", "Lista de tarifas por parqueadero");
    }

    public Optional<TarifaModel> obtenerTarifaParqueaderoVehiculo(Long parqueadero, Long vehiculo) {
        return tarifaRepository.findByParqueaderoAndVehiculo(parqueadero, vehiculo);
    }

    public TarifaModel crearFactura(TarifaModel tarifa) {
        return tarifaRepository.save(tarifa);
    }

    public Map<String, Object> obtenerTarifaParqueaderoVehiculoResponse(TarifaRequest tarifaRequest) {
        Optional<TarifaModel> tarifaParqueadero = obtenerTarifaParqueaderoVehiculo(tarifaRequest.getParqueadero_fk(), tarifaRequest.getVehiculo_fk());
        if (tarifaParqueadero.isPresent()) {
            int precioFinal = CalculoPrecioService.CalcularPrecio(tarifaParqueadero.get(), tarifaRequest.getHoras());
            return Map.of("data", Map.of("Precio", precioFinal), "msg", "Calculo de precio");
        }
        return Map.of("data", "", "msg", "Parqueadero o vehiculo no encontrado");
    }
}
