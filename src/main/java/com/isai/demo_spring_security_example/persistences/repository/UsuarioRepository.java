package com.isai.demo_spring_security_example.persistences.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isai.demo_spring_security_example.persistences.entity.Usuario;

@Repository
public interface UsuarioRepository
        extends CrudRepository<Usuario, Long> {
    // metodo para buscar un usuario en la base de datos por el nombre de usuario
    Optional<Usuario> findUsuarioByNombreUsuario(String nombreUsuario);

}