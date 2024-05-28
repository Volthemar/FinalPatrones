package com.api.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.dto.request.ParqueaderoRequest;
import com.api.crud.dto.response.ParqueaderoBasicoResponse;
import com.api.crud.dto.response.ParqueaderoEstadisticasResponse;
import com.api.crud.dto.response.ParqueaderoResponse;
import com.api.crud.models.ParqueaderoModel;
import com.api.crud.repositories.IFacturaRepository;
import com.api.crud.repositories.IFacturaOfflineRepository;
import com.api.crud.repositories.IParqueaderoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.Map;

@Service
public class ParqueaderoService {

    @Autowired
    private IParqueaderoRepository parqueaderoRepository;

    @Autowired
    private IFacturaRepository facturaRepository;

    @Autowired
    private IFacturaOfflineRepository facturaOfflineRepository;

    @Autowired
    private TipoParqueaderoService tipoParqueaderoService;

    public Vector<ParqueaderoModel> obtenerParqueaderoCiudad(Long ciudad) {
        return parqueaderoRepository.findByCiudad(ciudad);
    }

    public Optional<ParqueaderoModel> obtenerParqueadero(Long parqueadero) {
        return parqueaderoRepository.findById(parqueadero);
    }

    public ParqueaderoModel guardarParqueadero(ParqueaderoModel parqueadero) {
        return parqueaderoRepository.save(parqueadero);
    }

    public Map<String, Object> parqueaderoCiudad(ParqueaderoRequest ciudad) {
        Vector<ParqueaderoModel> parquedaeros = obtenerParqueaderoCiudad(ciudad.getCiudad_fk());
        Vector<ParqueaderoResponse> parqueaderos_disponibles = new Vector<>();
        for (ParqueaderoModel parqueadero : parquedaeros) {
            ParqueaderoResponse parqueadero_parcial = new ParqueaderoResponse();
            parqueadero_parcial.setId(parqueadero.getId());
            parqueadero_parcial.setNombre(parqueadero.getNombre());
            parqueadero_parcial.setLongitud(parqueadero.getLongitud());
            parqueadero_parcial.setLatitud(parqueadero.getLatitud());

            int total_carro = parqueadero.getCupo_carro_total();
            int total_bici = parqueadero.getCupo_bici_total();
            int total_moto = parqueadero.getCupo_moto_total();

            int utilizado_carro = parqueadero.getCupo_uti_carro();
            int utilizado_bici = parqueadero.getCupo_uti_bici();
            int utilizado_moto = parqueadero.getCupo_uti_moto();

            parqueadero_parcial.setCupo_disponible_carro(total_carro - utilizado_carro);
            parqueadero_parcial.setCupo_disponible_bici(total_bici - utilizado_bici);
            parqueadero_parcial.setCupo_disponible_moto(total_moto - utilizado_moto);

            int disponibilidad_total = total_carro + total_bici + total_moto;
            int utilizado_total = utilizado_carro + utilizado_bici + utilizado_moto;

            double porcentaje_ocupado = ((double) utilizado_total / (double) disponibilidad_total);

            if (porcentaje_ocupado >= 0 && porcentaje_ocupado < 0.6) {
                parqueadero_parcial.setColor("VERDE");
            } else if (porcentaje_ocupado >= 0.6 && porcentaje_ocupado < 1) {
                parqueadero_parcial.setColor("AMARILLO");
            } else if (porcentaje_ocupado == 1) {
                parqueadero_parcial.setColor("NEGRO");
            }

            parqueadero_parcial.setTipo(tipoParqueaderoService.obtenerTipo(parqueadero.getTipo_fk()));

            parqueaderos_disponibles.add(parqueadero_parcial);
        }

        return Map.of("data", parqueaderos_disponibles, "msg", "Parqueaderos");
    }

    public Map<String, Object> parqueaderoCiudadBasico(ParqueaderoRequest ciudad) {
        Vector<ParqueaderoModel> parquedaeros = obtenerParqueaderoCiudad(ciudad.getCiudad_fk());
        Vector<ParqueaderoBasicoResponse> parqueaderos_disponibles = new Vector<>();
        for (ParqueaderoModel parqueadero : parquedaeros) {
            ParqueaderoBasicoResponse parqueadero_parcial = new ParqueaderoBasicoResponse();
            parqueadero_parcial.setId(parqueadero.getId());
            parqueadero_parcial.setNombre(parqueadero.getNombre());
            parqueaderos_disponibles.add(parqueadero_parcial);
        }
        return Map.of("data", parqueaderos_disponibles, "msg", "Parqueaderos");
    }

    public Map<String, Object> obtenerParqueadero(ParqueaderoRequest parqueadero) {
        Optional<ParqueaderoModel> parquedaeros = obtenerParqueadero(parqueadero.getParqueadero_id());
        return Map.of("data", parquedaeros.get(), "msg", "Parqueaderos");
    }

    public Map<String, Object> guardarParqueadero(ParqueaderoRequest parqueadero) {
        ParqueaderoModel parqueaderoGuardado = new ParqueaderoModel();
        parqueaderoGuardado.setNombre(parqueadero.getNombre());
        parqueaderoGuardado.setCupo_bici_total(parqueadero.getCupo_bici_total());
        parqueaderoGuardado.setCupo_carro_total(parqueadero.getCupo_carro_total());
        parqueaderoGuardado.setCupo_moto_total(parqueadero.getCupo_moto_total());
        parqueaderoGuardado.setCiudad_fk(parqueadero.getCiudad_fk());
        parqueaderoGuardado.setTipo_fk(parqueadero.getTipo_fk());
        parqueaderoGuardado.setLongitud(parqueadero.getLongitud());
        parqueaderoGuardado.setLatitud(parqueadero.getLatitud());
        parqueaderoGuardado.setCupo_uti_bici(0);
        parqueaderoGuardado.setCupo_uti_carro(0);
        parqueaderoGuardado.setCupo_uti_moto(0);
        parqueaderoGuardado.setFecha_creacion(ManejarFechas.obtenerFechaActual());
        parqueaderoGuardado.setActivo(true);
        guardarParqueadero(parqueaderoGuardado);
        return Map.of("data", parqueaderoGuardado, "msg", "Parqueaderos");
    }

    public Optional<ParqueaderoEstadisticasResponse> obtenerEstadisticasParqueadero(long parqueaderoId) {
        Optional<ParqueaderoModel> parqueaderoOpt = parqueaderoRepository.findById(parqueaderoId);

        if (parqueaderoOpt.isPresent()) {
            ParqueaderoModel parqueadero = parqueaderoOpt.get();

            List<String> labels = new ArrayList<>();
            List<Integer> CuposTotales = new ArrayList<>();
            List<Integer> CuposOcupados = new ArrayList<>();
            List<Integer> CuposDisponibles = new ArrayList<>();
            List<Integer> Ingresos = new ArrayList<>();

            labels.add("CARRO");
            labels.add("MOTO");
            labels.add("BICI");

            agregarEstadisticasPorVehiculo(parqueadero, "CARRO", CuposTotales, CuposOcupados, CuposDisponibles, Ingresos);
            agregarEstadisticasPorVehiculo(parqueadero, "MOTO", CuposTotales, CuposOcupados, CuposDisponibles, Ingresos);
            agregarEstadisticasPorVehiculo(parqueadero, "BICI", CuposTotales, CuposOcupados, CuposDisponibles, Ingresos);

            ParqueaderoEstadisticasResponse response = new ParqueaderoEstadisticasResponse();
            response.setLabels(labels);
            response.setCuposTotales(CuposTotales);
            response.setCuposOcupados(CuposOcupados);
            response.setCuposDisponibles(CuposDisponibles);
            response.setIngresos(Ingresos);

            return Optional.of(response);
        } else {
            return Optional.empty();
        }
    }

    public ParqueaderoEstadisticasResponse obtenerEstadisticasGlobales() {
        List<ParqueaderoModel> parqueaderos = parqueaderoRepository.findAll();

        List<String> labels = new ArrayList<>();
        List<Integer> CuposTotales = new ArrayList<>();
        List<Integer> CuposOcupados = new ArrayList<>();
        List<Integer> CuposDisponibles = new ArrayList<>();
        List<Integer> Ingresos = new ArrayList<>();

        labels.add("CARRO");
        labels.add("MOTO");
        labels.add("BICI");

        int totalCuposCarro = 0;
        int cuposOcupadosCarro = 0;
        int ingresosCarro = 0;

        int totalCuposMoto = 0;
        int cuposOcupadosMoto = 0;
        int ingresosMoto = 0;

        int totalCuposBici = 0;
        int cuposOcupadosBici = 0;
        int ingresosBici = 0;

        for (ParqueaderoModel parqueadero : parqueaderos) {
            Optional<ParqueaderoEstadisticasResponse> estadisticasOpt = obtenerEstadisticasParqueadero(parqueadero.getId());
            if (estadisticasOpt.isPresent()) {
                ParqueaderoEstadisticasResponse estadisticas = estadisticasOpt.get();

                totalCuposCarro += estadisticas.getCuposTotales().get(0);
                cuposOcupadosCarro += estadisticas.getCuposOcupados().get(0);
                ingresosCarro += estadisticas.getIngresos().get(0);

                totalCuposMoto += estadisticas.getCuposTotales().get(1);
                cuposOcupadosMoto += estadisticas.getCuposOcupados().get(1);
                ingresosMoto += estadisticas.getIngresos().get(1);

                totalCuposBici += estadisticas.getCuposTotales().get(2);
                cuposOcupadosBici += estadisticas.getCuposOcupados().get(2);
                ingresosBici += estadisticas.getIngresos().get(2);
            }
        }

        CuposTotales.add(totalCuposCarro);
        CuposOcupados.add(cuposOcupadosCarro);
        CuposDisponibles.add(totalCuposCarro - cuposOcupadosCarro);
        Ingresos.add(ingresosCarro);

        CuposTotales.add(totalCuposMoto);
        CuposOcupados.add(cuposOcupadosMoto);
        CuposDisponibles.add(totalCuposMoto - cuposOcupadosMoto);
        Ingresos.add(ingresosMoto);

        CuposTotales.add(totalCuposBici);
        CuposOcupados.add(cuposOcupadosBici);
        CuposDisponibles.add(totalCuposBici - cuposOcupadosBici);
        Ingresos.add(ingresosBici);

        ParqueaderoEstadisticasResponse response = new ParqueaderoEstadisticasResponse();
        response.setLabels(labels);
        response.setCuposTotales(CuposTotales);
        response.setCuposOcupados(CuposOcupados);
        response.setCuposDisponibles(CuposDisponibles);
        response.setIngresos(Ingresos);

        return response;
    }

    private void agregarEstadisticasPorVehiculo(ParqueaderoModel parqueadero, String tipoVehiculo,
                                                List<Integer> CuposTotales, List<Integer> CuposOcupados,
                                                List<Integer> CuposDisponibles, List<Integer> Ingresos) {
        int totalCupos;
        int cuposOcupados;
        switch (tipoVehiculo) {
            case "CARRO":
                totalCupos = parqueadero.getCupo_carro_total();
                cuposOcupados = parqueadero.getCupo_uti_carro();
                break;
            case "MOTO":
                totalCupos = parqueadero.getCupo_moto_total();
                cuposOcupados = parqueadero.getCupo_uti_moto();
                break;
            case "BICI":
                totalCupos = parqueadero.getCupo_bici_total();
                cuposOcupados = parqueadero.getCupo_uti_bici();
                break;
            default:
                totalCupos = 0;
                cuposOcupados = 0;
        }

        int cuposDisponibles = totalCupos - cuposOcupados;
        Integer ingresosGeneradosPorFacturaOnline = facturaRepository.sumByParqueaderoIdAndVehiculoTipo(parqueadero.getId(), tipoVehiculo);
        if (ingresosGeneradosPorFacturaOnline == null) {
            ingresosGeneradosPorFacturaOnline = 0;
        }
        Integer ingresosGeneradosPorFacturaOffline = facturaOfflineRepository.sumByParqueaderoIdAndVehiculoTipo(parqueadero.getId(), tipoVehiculo);
        if (ingresosGeneradosPorFacturaOffline == null) {
            ingresosGeneradosPorFacturaOffline = 0;
        }

        int ingresosGenerados = ingresosGeneradosPorFacturaOnline + ingresosGeneradosPorFacturaOffline;

        CuposTotales.add(totalCupos);
        CuposOcupados.add(cuposOcupados);
        CuposDisponibles.add(cuposDisponibles);
        Ingresos.add(ingresosGenerados);
    }
}
