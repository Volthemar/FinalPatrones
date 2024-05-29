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

    private static final String parqueaderos = "Parqueaderos";
    private static final String carro = "CARRO";
    private static final String moto = "MOTO";
    private static final String bici = "BICI";

    public ArrayList<ParqueaderoModel> obtenerParqueaderoCiudad(Long ciudad) {
        return parqueaderoRepository.findByCiudad(ciudad);
    }

    public Optional<ParqueaderoModel> obtenerParqueadero(Long parqueadero) {
        return parqueaderoRepository.findById(parqueadero);
    }

    public ParqueaderoModel guardarParqueadero(ParqueaderoModel parqueadero) {
        return parqueaderoRepository.save(parqueadero);
    }

    public Map<String, Object> parqueaderoCiudad(ParqueaderoRequest ciudad) {
        ArrayList<ParqueaderoModel> parquedaeros = obtenerParqueaderoCiudad(ciudad.getCiudad_fk());
        ArrayList<ParqueaderoResponse> parqueaderos_disponibles = new ArrayList<>();
        for (ParqueaderoModel parqueadero : parquedaeros) {
            ParqueaderoResponse parqueaderoParcial = new ParqueaderoResponse();
            parqueaderoParcial.setId(parqueadero.getId());
            parqueaderoParcial.setNombre(parqueadero.getNombre());
            parqueaderoParcial.setLongitud(parqueadero.getLongitud());
            parqueaderoParcial.setLatitud(parqueadero.getLatitud());

            int totalCarro = parqueadero.getCupo_carro_total();
            int totalBici = parqueadero.getCupo_bici_total();
            int totalMoto = parqueadero.getCupo_moto_total();

            int utilizadoCarro = parqueadero.getCupo_uti_carro();
            int utilizadoBici = parqueadero.getCupo_uti_bici();
            int utilizadoMoto = parqueadero.getCupo_uti_moto();

            parqueaderoParcial.setCupo_disponible_carro(totalCarro - utilizadoCarro);
            parqueaderoParcial.setCupo_disponible_bici(totalBici - utilizadoBici);
            parqueaderoParcial.setCupo_disponible_moto(totalMoto - utilizadoMoto);

            int disponibilidadTotal = totalCarro + totalBici + totalMoto;
            int utilizadoTotal = utilizadoCarro + utilizadoBici + utilizadoMoto;

            double porcentaje_ocupado = ((double) utilizadoTotal / (double) disponibilidadTotal);

            if (porcentaje_ocupado >= 0 && porcentaje_ocupado < 0.6) {
                parqueaderoParcial.setColor("VERDE");
            } else if (porcentaje_ocupado >= 0.6 && porcentaje_ocupado < 1) {
                parqueaderoParcial.setColor("AMARILLO");
            } else if (porcentaje_ocupado == 1) {
                parqueaderoParcial.setColor("NEGRO");
            }

            parqueaderoParcial.setTipo(tipoParqueaderoService.obtenerTipo(parqueadero.getTipo_fk()));

            parqueaderos_disponibles.add(parqueaderoParcial);
        }

        return Map.of("data", parqueaderos_disponibles, "msg", parqueaderos);
    }

    public Map<String, Object> parqueaderoCiudadBasico(ParqueaderoRequest ciudad) {
        ArrayList<ParqueaderoModel> parquedaeros = obtenerParqueaderoCiudad(ciudad.getCiudad_fk());
        ArrayList<ParqueaderoBasicoResponse> parqueaderos_disponibles = new ArrayList<>();
        for (ParqueaderoModel parqueadero : parquedaeros) {
            ParqueaderoBasicoResponse parqueaderoParcial = new ParqueaderoBasicoResponse();
            parqueaderoParcial.setId(parqueadero.getId());
            parqueaderoParcial.setNombre(parqueadero.getNombre());
            parqueaderos_disponibles.add(parqueaderoParcial);
        }
        return Map.of("data", parqueaderos_disponibles, "msg", parqueaderos);
    }

    public Map<String, Object> obtenerParqueadero(ParqueaderoRequest parqueadero) {
        Optional<ParqueaderoModel> parquedaeros = obtenerParqueadero(parqueadero.getParqueadero_id());
        return Map.of("data", parquedaeros.get(), "msg", parqueaderos);
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
        return Map.of("data", parqueaderoGuardado, "msg", parqueaderos);
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

            labels.add(carro);
            labels.add(moto);
            labels.add(bici);

            agregarEstadisticasPorVehiculo(parqueadero, carro, CuposTotales, CuposOcupados, CuposDisponibles, Ingresos);
            agregarEstadisticasPorVehiculo(parqueadero, moto, CuposTotales, CuposOcupados, CuposDisponibles, Ingresos);
            agregarEstadisticasPorVehiculo(parqueadero, bici, CuposTotales, CuposOcupados, CuposDisponibles, Ingresos);

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

        labels.add(carro);
        labels.add(moto);
        labels.add(bici);

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
            case carro:
                totalCupos = parqueadero.getCupo_carro_total();
                cuposOcupados = parqueadero.getCupo_uti_carro();
                break;
            case moto:
                totalCupos = parqueadero.getCupo_moto_total();
                cuposOcupados = parqueadero.getCupo_uti_moto();
                break;
            case bici:
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
