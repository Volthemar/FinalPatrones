package com.api.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.crud.models.TipoUsuarioModel;

public interface ITipoUsuarioRepository extends JpaRepository<TipoUsuarioModel,Long>{

}
