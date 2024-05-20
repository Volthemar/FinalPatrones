package com.api.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.crud.models.CupoModel;

public interface ICupoRepository extends JpaRepository<CupoModel,Long>{

}
