package com.api.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.api.crud.models.VehiculoModel;

public interface IVehiculoRepository extends JpaRepository<VehiculoModel, Long> {

    List<VehiculoModel> findByActivo(boolean activo);

}
