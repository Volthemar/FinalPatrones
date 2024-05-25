package com.api.crud.services;

import java.util.Map;
import java.util.Vector;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.DTO.Response.ParqueaderoEstadisticasResponse;
import com.api.crud.models.ParqueaderoModel;
import com.api.crud.repositories.ICupoOfflineRepository;
import com.api.crud.repositories.ICupoRepository;
import com.api.crud.repositories.IParqueaderoRepository;
import com.api.crud.repositories.IFacturaRepository;
import com.api.crud.repositories.IFacturaOfflineRepository;


@Service
public class ParqueaderoService {
    @Autowired
    IParqueaderoRepository parqueaderoRepository;
    
    @Autowired
    private ICupoRepository cupoRepository;

    @Autowired
    private ICupoOfflineRepository cupoOfflineRepository;

    @Autowired
    private IFacturaRepository facturaRepository;

    @Autowired
    private IFacturaOfflineRepository facturaOfflineRepository;

    public Vector<ParqueaderoModel> obtenerParqueaderoCiudad(Long ciudad) {
        return parqueaderoRepository.findByCiudad(ciudad);
    }

    public Optional<ParqueaderoModel> obtenerParqueadero(Long parqueadero) {
        return parqueaderoRepository.findById(parqueadero);
    }

    public ParqueaderoModel guardarParqueadero(ParqueaderoModel parqueadero) {
        return parqueaderoRepository.save(parqueadero);
    }

    public ParqueaderoEstadisticasResponse obtenerEstadisticas(long parqueaderoId) {
        ParqueaderoModel parqueadero = parqueaderoRepository.findById(parqueaderoId).orElse(null);

        if (parqueadero == null) {
            return null; // Handle appropriately
        }

        int totalCuposCarro = parqueadero.getCupo_carro_total();
        int totalCuposMoto = parqueadero.getCupo_moto_total();
        int totalCuposBici = parqueadero.getCupo_bici_total();

        int cuposOcupadosCarro = cupoRepository.countByParqueaderoIdAndVehiculoTipo(parqueaderoId, "CARRO");
        int cuposOcupadosMoto = cupoRepository.countByParqueaderoIdAndVehiculoTipo(parqueaderoId, "MOTO");
        int cuposOcupadosBici = cupoRepository.countByParqueaderoIdAndVehiculoTipo(parqueaderoId, "BICI");

        int cuposOcupadosCarroOffline = cupoOfflineRepository.countByParqueaderoIdAndVehiculoTipo(parqueaderoId, "CARRO");
        int cuposOcupadosMotoOffline = cupoOfflineRepository.countByParqueaderoIdAndVehiculoTipo(parqueaderoId, "MOTO");
        int cuposOcupadosBiciOffline = cupoOfflineRepository.countByParqueaderoIdAndVehiculoTipo(parqueaderoId, "BICI");

        cuposOcupadosCarro += cuposOcupadosCarroOffline;
        cuposOcupadosMoto += cuposOcupadosMotoOffline;
        cuposOcupadosBici += cuposOcupadosBiciOffline;

        Map<String, Integer> ingresosPorVehiculo = new HashMap<>();
        ingresosPorVehiculo.put("CARRO", 
            facturaRepository.sumByParqueaderoIdAndVehiculoTipo(parqueaderoId, "CARRO") + 
            facturaOfflineRepository.sumByParqueaderoIdAndVehiculoTipo(parqueaderoId, "CARRO"));
        ingresosPorVehiculo.put("MOTO", 
            facturaRepository.sumByParqueaderoIdAndVehiculoTipo(parqueaderoId, "MOTO") + 
            facturaOfflineRepository.sumByParqueaderoIdAndVehiculoTipo(parqueaderoId, "MOTO"));
        ingresosPorVehiculo.put("BICI", 
            facturaRepository.sumByParqueaderoIdAndVehiculoTipo(parqueaderoId, "BICI") + 
            facturaOfflineRepository.sumByParqueaderoIdAndVehiculoTipo(parqueaderoId, "BICI"));

        ParqueaderoEstadisticasResponse response = new ParqueaderoEstadisticasResponse();
        response.setParqueaderoId(parqueaderoId);
        response.setTotalCuposCarro(totalCuposCarro);
        response.setTotalCuposMoto(totalCuposMoto);
        response.setTotalCuposBici(totalCuposBici);
        response.setCuposOcupadosCarro(cuposOcupadosCarro);
        response.setCuposOcupadosMoto(cuposOcupadosMoto);
        response.setCuposOcupadosBici(cuposOcupadosBici);
        response.setIngresosPorVehiculo(ingresosPorVehiculo);

        return response;
    }

}
