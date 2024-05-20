package com.api.crud.repositories;

import java.util.Vector;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.crud.models.TarifaModel;

public interface ITarifaRepository extends JpaRepository<TarifaModel,Long>{
    @Query(value = "Select * from tarifa where Parqueadero_fk = ?1", nativeQuery = true)
    Vector<TarifaModel> findByParqueadero(Long parqueadero);

    @Query(value = "Select * from tarifa where Parqueadero_fk = ?1 and Vehiculo_fk = ?2", nativeQuery = true)
    Vector<TarifaModel> findByParqueaderoAndVehiculo(Long parqueadero, Long vehiculo);
}
