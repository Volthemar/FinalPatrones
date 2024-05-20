package com.api.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.crud.models.VehiculoModel;

public interface IVehiculoRepository extends JpaRepository<VehiculoModel,Long>{

}
