package com.api.crud.repositories;

import com.api.crud.models.IpModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IpRepository extends JpaRepository<IpModel, Long> {
    List<IpModel> findByUsuarioFk(Long usuarioFk);

    List<IpModel> findByDireccionIp(String direccionIp);

    List<IpModel> findByActivo(boolean activo);
}
