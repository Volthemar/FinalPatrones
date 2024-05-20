package com.api.crud.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.api.crud.DTO.ParqueaderoBasicoResponse;
import com.api.crud.DTO.ParqueaderoRequest;
import com.api.crud.DTO.ParqueaderoResponse;
import com.api.crud.models.ParqueaderoModel;
import com.api.crud.services.ManejarFechas;
import com.api.crud.services.ParqueaderoService;
import com.api.crud.services.TipoParqueaderoService;
import java.util.Optional;
import java.util.Map;
import java.util.Vector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("")
public class ParqueaderoController {
    @Autowired
    private ParqueaderoService parqueaderoService;

    @Autowired
    private TipoParqueaderoService tipoParqueaderoService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/parqueaderoCiudad")
    public Map<String,Object> parqueaderoCiudad(@RequestBody ParqueaderoRequest ciudad){
        Vector<ParqueaderoModel> parquedaeros = parqueaderoService.obtenerParqueaderoCiudad(ciudad.getCiudad_fk());
        Vector<ParqueaderoResponse> parqueaderos_disponibles = new Vector<>();
        for(int i=0;i<parquedaeros.size();i++){
            ParqueaderoResponse parqueadero_parcial = new ParqueaderoResponse();
            parqueadero_parcial.setId(parquedaeros.get(i).getId());
            parqueadero_parcial.setNombre(parquedaeros.get(i).getNombre());
            parqueadero_parcial.setLongitud(parquedaeros.get(i).getLongitud());
            parqueadero_parcial.setLatitud(parquedaeros.get(i).getLatitud());

            int total_carro = parquedaeros.get(i).getCupo_carro_total();
            int total_bici = parquedaeros.get(i).getCupo_bici_total();
            int total_moto = parquedaeros.get(i).getCupo_moto_total();

            int utilizado_carro = parquedaeros.get(i).getCupo_uti_carro();
            int utilizado_bici = parquedaeros.get(i).getCupo_uti_bici();
            int utilizado_moto = parquedaeros.get(i).getCupo_uti_moto();

            parqueadero_parcial.setCupo_disponible_carro(total_carro-utilizado_carro);
            parqueadero_parcial.setCupo_disponible_bici(total_bici-utilizado_bici);
            parqueadero_parcial.setCupo_disponible_moto(total_moto-utilizado_moto);

            int disponibilidad_total = total_carro+total_bici+total_moto;
            int utilizado_total = utilizado_carro+utilizado_bici+utilizado_moto;

            double porcentaje_ocupado = ((double) utilizado_total / (double) (disponibilidad_total ));

            if (porcentaje_ocupado >= 0 && porcentaje_ocupado < 0.6){
                parqueadero_parcial.setColor("VERDE");
            }else if(porcentaje_ocupado >= 0.6 && porcentaje_ocupado < 1){
                parqueadero_parcial.setColor("AMARILLO");
            }else if(porcentaje_ocupado == 1){
                parqueadero_parcial.setColor("NEGRO");
            }

            parqueadero_parcial.setTipo(tipoParqueaderoService.obtenerTipo(parquedaeros.get(i).getTipo_fk()));

            parqueaderos_disponibles.add(parqueadero_parcial);
        }
        
        return Map.of("data", parqueaderos_disponibles, "msg", "Parqueaderos");
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/parqueaderoCiudadBasico")
    public Map<String,Object> parqueaderoCiudadBasico(@RequestBody ParqueaderoRequest ciudad){
        Vector<ParqueaderoModel> parquedaeros = parqueaderoService.obtenerParqueaderoCiudad(ciudad.getCiudad_fk());
        Vector<ParqueaderoBasicoResponse> parqueaderos_disponibles = new Vector<>();
        for(int i=0;i<parquedaeros.size();i++){
            ParqueaderoBasicoResponse parqueadero_parcial = new ParqueaderoBasicoResponse();
            parqueadero_parcial.setId(parquedaeros.get(i).getId());
            parqueadero_parcial.setNombre(parquedaeros.get(i).getNombre());
            parqueaderos_disponibles.add(parqueadero_parcial);
        }
        return Map.of("data", parqueaderos_disponibles, "msg", "Parqueaderos");
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/obtenerParqueadero")
    public Map<String,Object> obtenerParqueadero(@RequestBody ParqueaderoRequest parqueadero){
        Optional<ParqueaderoModel> parquedaeros = parqueaderoService.obtenerParqueadero(parqueadero.getParqueadero_id());
        return Map.of("data", parquedaeros.get(), "msg", "Parqueaderos");
    }
    
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/guardarParqueadero")
    public Map<String,Object> guardarParqueadero(@RequestBody ParqueaderoRequest parqueadero){
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
        parqueaderoService.guardarParqueadero(parqueaderoGuardado);
        return Map.of("data", parqueaderoGuardado, "msg", "Parqueaderos");
    }

}