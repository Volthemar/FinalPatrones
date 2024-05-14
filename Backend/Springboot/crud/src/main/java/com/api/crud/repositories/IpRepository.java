package com.api.crud.repositories;

import com.api.crud.models.Ip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpRepository extends JpaRepository<Ip, Long> {
    List<Ip> findByUsuarioFk(Long usuarioFk);
    List<Ip> findByDireccionIp(String direccionIp);
}
