package com.api.crud.repositories;

import java.util.Vector;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.crud.models.ParqueaderoModel;

@Repository
public interface IParqueaderoRepository extends JpaRepository<ParqueaderoModel,Long>{
    @Query(value = "Select * from parqueadero where ciudad_fk = ?1", nativeQuery = true)
    Vector<ParqueaderoModel> findByCiudad(Long ciudad);    
}
