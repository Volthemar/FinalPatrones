package com.example.Hola.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Hola.models.UserModel;
import com.example.Hola.repositories.IUserRepository;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;
    
    public ArrayList<UserModel> getUser(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel guardarUsuario(UserModel usuario){
        return userRepository.save(usuario);
    }

    public Optional<UserModel> getPorId(Long id){
        return userRepository.findById(id);
    }
}
