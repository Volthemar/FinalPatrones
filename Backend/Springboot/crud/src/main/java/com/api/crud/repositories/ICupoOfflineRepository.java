package com.api.crud.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.crud.models.CupoOfflineModel;

@Repository
public interface ICupoOfflineRepository extends JpaRepository<CupoOfflineModel, Long> {

    @Query("SELECT COUNT(c) FROM CupoOfflineModel c JOIN VehiculoModel v ON c.vehiculo_fk = v.id WHERE c.parqueadero_fk = :parqueaderoId AND v.tipo = :vehicleType")
    int countByParqueaderoIdAndVehiculoTipo(@Param("parqueaderoId") long parqueaderoId,
            @Param("vehicleType") String vehicleType);

    List<CupoOfflineModel> findByActivo(boolean activo);

    Optional<CupoOfflineModel> findByCodigo(String Codigo);

    @Query(value = "SELECT * FROM CUPO_OFFLINE WHERE estado = 'OCUPADO' AND parqueadero_fk = ?1 AND vehiculo_fk = ?2", nativeQuery = true)
    List<CupoOfflineModel> findByParqueaderoAndVehiculoOcupado(Long parqueaderoId, Long vehiculoId);
}