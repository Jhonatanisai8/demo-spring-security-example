package com.isai.demo_spring_security_example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.isai.demo_spring_security_example.persistences.entity.Usuario;
import com.isai.demo_spring_security_example.persistences.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuarioBD = usuarioRepository.findUsuarioByNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("EL usuario " + username + " no se encontro."));
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        usuarioBD.getRoles()
                .forEach(rol -> simpleGrantedAuthorities
                        .add(new SimpleGrantedAuthority("ROLE_".concat(rol.getNombreRol().name()))));
        usuarioBD.getRoles()
                .stream()
                .flatMap(rol -> rol.getPermisos()
                        .stream())
                .forEach(permiso -> simpleGrantedAuthorities
                        .add(new SimpleGrantedAuthority(permiso.getNombrePermiso())));
        return new User(
                usuarioBD.getNombreUsuario(),
                usuarioBD.getPassword(),
                usuarioBD.getEstaHabilitada(),
                usuarioBD.getCuentaAspirada(),
                usuarioBD.getCredencialesExpiradas(),
                usuarioBD.getCuentaBloqueda(),
                simpleGrantedAuthorities);
    }

}