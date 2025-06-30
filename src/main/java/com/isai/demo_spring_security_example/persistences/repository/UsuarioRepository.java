package com.isai.demo_spring_security_example.persistences.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isai.demo_spring_security_example.persistences.entity.Usuario;

@Repository
public interface UsuarioRepository
        extends CrudRepository<Usuario, Long> {

}