package com.api.crud.repositories;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.crud.models.UsuarioModel;

@Repository

public interface IUsuarioRepository extends JpaRepository<UsuarioModel, Long> {
   
    Optional<UsuarioModel> findByUsuarioAndContrasena(String usuario,String contrasena);
    Optional<UsuarioModel> findByUsuario(String usuario);
}
