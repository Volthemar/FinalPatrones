package com.example.Hola.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Hola.models.UserModel;

@Repository

public interface IUserRepository extends JpaRepository<UserModel,Long>{

}
