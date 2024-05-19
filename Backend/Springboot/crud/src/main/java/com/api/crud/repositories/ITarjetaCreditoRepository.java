package com.api.crud.repositories;

import java.util.Vector;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.crud.models.TarjetaCreditoModel;

@Repository
public interface ITarjetaCreditoRepository extends JpaRepository<TarjetaCreditoModel, Long> {
    @Query(value = "Select * from tarjeta_credito where Usuario_fk = ?1", nativeQuery = true)
    Vector<TarjetaCreditoModel> findByUsuario(Long usuario);
}
