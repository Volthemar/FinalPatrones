package com.api.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.crud.models.TipoParqueaderoModel;

@Repository
public interface ITipoParqueaderoRepository extends JpaRepository<TipoParqueaderoModel,Long>{

}
