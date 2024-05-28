package com.api.crud.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.crud.models.TipoUsuarioModel;

public interface ITipoUsuarioRepository extends JpaRepository<TipoUsuarioModel, Long> {
    @Query(value = "Select t.Id FROM TipoUsuarioModel t WHERE t.tipo = 'cliente'")
    Long findIdCliente();

    List<TipoUsuarioModel> findByActivo(boolean activo);
}
