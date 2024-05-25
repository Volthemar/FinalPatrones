package com.api.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.DTO.Response.ParqueaderoEstadisticasResponse;
import com.api.crud.models.ParqueaderoModel;
import com.api.crud.repositories.IFacturaRepository;
import com.api.crud.repositories.IFacturaOfflineRepository;
import com.api.crud.repositories.IParqueaderoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ParqueaderoService {

    private static final Logger logger = Logger.getLogger(ParqueaderoService.class.getName());

    @Autowired
    private IParqueaderoRepository parqueaderoRepository;

    @Autowired
    private IFacturaRepository facturaRepository;

    @Autowired
    private IFacturaOfflineRepository facturaOfflineRepository;

    public Optional<ParqueaderoEstadisticasResponse> obtenerEstadisticasParqueadero(long parqueaderoId) {
        logger.info("Fetching parqueadero with ID: " + parqueaderoId);
        Optional<ParqueaderoModel> parqueaderoOpt = parqueaderoRepository.findById(parqueaderoId);

        if (parqueaderoOpt.isPresent()) {
            ParqueaderoModel parqueadero = parqueaderoOpt.get();

            List<String> labels = new ArrayList<>();
            List<Integer> cuposTotales = new ArrayList<>();
            List<Integer> cuposOcupados = new ArrayList<>();
            List<Integer> cuposDisponibles = new ArrayList<>();
            List<Integer> ingresos = new ArrayList<>();

            labels.add("CARRO");
            labels.add("MOTO");
            labels.add("BICI");

            int totalCuposCarro = parqueadero.getCupo_carro_total();
            int cuposOcupadosCarro = parqueadero.getCupo_uti_carro();
            int cuposDisponiblesCarro = totalCuposCarro - cuposOcupadosCarro;
            int ingresosCarro = facturaRepository.sumByParqueaderoIdAndVehiculoTipo(parqueaderoId, "CARRO") +
                                facturaOfflineRepository.sumByParqueaderoIdAndVehiculoTipo(parqueaderoId, "CARRO");

            cuposTotales.add(totalCuposCarro);
            cuposOcupados.add(cuposOcupadosCarro);
            cuposDisponibles.add(cuposDisponiblesCarro);
            ingresos.add(ingresosCarro);

            int totalCuposMoto = parqueadero.getCupo_moto_total();
            int cuposOcupadosMoto = parqueadero.getCupo_uti_moto();
            int cuposDisponiblesMoto = totalCuposMoto - cuposOcupadosMoto;
            int ingresosMoto = facturaRepository.sumByParqueaderoIdAndVehiculoTipo(parqueaderoId, "MOTO") +
                               facturaOfflineRepository.sumByParqueaderoIdAndVehiculoTipo(parqueaderoId, "MOTO");

            cuposTotales.add(totalCuposMoto);
            cuposOcupados.add(cuposOcupadosMoto);
            cuposDisponibles.add(cuposDisponiblesMoto);
            ingresos.add(ingresosMoto);

            int totalCuposBici = parqueadero.getCupo_bici_total();
            int cuposOcupadosBici = parqueadero.getCupo_uti_bici();
            int cuposDisponiblesBici = totalCuposBici - cuposOcupadosBici;
            int ingresosBici = facturaRepository.sumByParqueaderoIdAndVehiculoTipo(parqueaderoId, "BICI") +
                               facturaOfflineRepository.sumByParqueaderoIdAndVehiculoTipo(parqueaderoId, "BICI");

            cuposTotales.add(totalCuposBici);
            cuposOcupados.add(cuposOcupadosBici);
            cuposDisponibles.add(cuposDisponiblesBici);
            ingresos.add(ingresosBici);

            ParqueaderoEstadisticasResponse response = new ParqueaderoEstadisticasResponse();
            response.setLabels(labels);
            response.setCuposTotales(cuposTotales);
            response.setCuposOcupados(cuposOcupados);
            response.setCuposDisponibles(cuposDisponibles);
            response.setIngresos(ingresos);

            return Optional.of(response);
        } else {
            logger.warning("Parqueadero not found with ID: " + parqueaderoId);
            return Optional.empty();
        }
    }
}

