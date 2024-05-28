package com.api.crud.repositories;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.crud.models.TarifaModel;

public interface ITarifaRepository extends JpaRepository<TarifaModel, Long> {
    @Query(value = "SELECT * FROM tarifa WHERE Parqueadero_fk = ?1 AND Vehiculo_fk = ?2", nativeQuery = true)
    Optional<TarifaModel> findByParqueaderoAndVehiculo(Long parqueadero, Long vehiculo);

    List<TarifaModel> findByActivo(boolean activo);

    @Query(value = "SELECT * FROM tarifa WHERE Parqueadero_fk = ?1", nativeQuery = true)
    List<TarifaModel> findByParqueadero(Long parqueadero);
}
