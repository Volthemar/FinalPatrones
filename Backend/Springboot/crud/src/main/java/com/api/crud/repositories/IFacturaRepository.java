package com.api.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.crud.models.FacturaModel;

@Repository
public interface IFacturaRepository extends JpaRepository<FacturaModel, Long> {

    @Query("SELECT SUM(f.valorPagado) FROM FacturaModel f JOIN f.vehiculo v WHERE f.parqueaderoId = :parqueaderoId AND v.tipo = :vehiculoTipo")
    int sumByParqueaderoIdAndVehiculoTipo(@Param("parqueaderoId") long parqueaderoId, @Param("vehiculoTipo") String vehiculoTipo);
}