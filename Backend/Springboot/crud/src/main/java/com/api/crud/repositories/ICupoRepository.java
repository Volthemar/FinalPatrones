package com.api.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.crud.models.CupoModel;

import java.util.Optional;

@Repository
public interface ICupoRepository extends JpaRepository<CupoModel,Long>{
    Optional<CupoModel> findByIdAndEstado(Long id, CupoModel.Estado status);

   @Query("SELECT COUNT(c) FROM CupoModel c JOIN VehiculoModel v ON c.vehiculo_fk = v.id WHERE c.parqueadero_fk = :parqueaderoId AND v.tipo = :vehicleType")
   int countByParqueaderoIdAndVehicleType(@Param("parqueaderoId") long parqueaderoId, @Param("vehicleType") String vehicleType);

   @Query("SELECT v.tipo FROM VehiculoModel v WHERE v.id = :vehicleId")
   String findVehicleTypeById(@Param("vehicleId") long vehicleId);
}
