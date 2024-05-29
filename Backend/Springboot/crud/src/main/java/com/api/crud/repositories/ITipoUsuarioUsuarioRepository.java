package com.api.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;
import java.util.List;
import com.api.crud.models.TipoUsuarioUsuarioModel;

public interface ITipoUsuarioUsuarioRepository extends JpaRepository<TipoUsuarioUsuarioModel, Long> {
    @Query(value = "Select * from tipo_usuario_usuario where Usuario_fk = ?1", nativeQuery = true)
    ArrayList<TipoUsuarioUsuarioModel> findByUsuario(Long usuario);

    List<TipoUsuarioUsuarioModel> findByActivo(boolean activo);
}
