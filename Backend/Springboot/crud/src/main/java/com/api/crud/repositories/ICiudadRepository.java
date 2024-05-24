package com.api.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.api.crud.models.CiudadModel;

@Repository
public interface ICiudadRepository extends JpaRepository<CiudadModel, Long> {

    List<CiudadModel> findByActivo(boolean activo);

}
