package com.api.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.api.crud.models.ParqueaderoUsuarioModel;

public interface IParqueaderoUsuarioRepository extends JpaRepository<ParqueaderoUsuarioModel,Long>{
    @Query(value = "Select p.parqueadero_fk From ParqueaderoUsuarioModel p WHERE p.usuario_fk = ?1")
    Long findByUsuario(Long usuario);
}
