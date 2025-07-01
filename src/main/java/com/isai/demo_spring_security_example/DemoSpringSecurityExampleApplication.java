package com.isai.demo_spring_security_example;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.isai.demo_spring_security_example.persistences.entity.Permiso;
import com.isai.demo_spring_security_example.persistences.entity.Roles;
import com.isai.demo_spring_security_example.persistences.entity.Usuario;
import com.isai.demo_spring_security_example.persistences.enums.RolEnum;
import com.isai.demo_spring_security_example.persistences.repository.UsuarioRepository;

@SpringBootApplication
public class DemoSpringSecurityExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringSecurityExampleApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository) {
		return args -> {
			// creamos los permisos
			Permiso permisoCrear = Permiso.builder()
					.nombrePermiso("CREATE")
					.build();
			Permiso permisoLeer = Permiso.builder()
					.nombrePermiso("READ")
					.build();
			Permiso permisoEliminar = Permiso.builder()
					.nombrePermiso("UPDATE")
					.build();
			Permiso permisoActualizar = Permiso.builder()
					.nombrePermiso("DELETE")
					.build();
			Permiso permisoCambiar = Permiso.builder()
					.nombrePermiso("REFACTOR")
					.build();

			// creacion de roles
			Roles rolAdmin = Roles.builder()
					.nombreRol(RolEnum.ADMIN)
					.permisos(Set.of(permisoCrear, permisoActualizar, permisoLeer, permisoEliminar))
					.build();

			Roles rolDesarrollador = Roles.builder()
					.nombreRol(RolEnum.DEVELOPER)
					.permisos(Set.of(permisoCrear, permisoLeer, permisoActualizar, permisoEliminar, permisoCambiar))
					.build();

			Roles rolInvitado = Roles.builder()
					.nombreRol(RolEnum.INVITADO)
					.permisos(Set.of(permisoLeer))
					.build();

			Roles rolUsuario = Roles.builder()
					.nombreRol(RolEnum.USER)
					.permisos(Set.of(permisoCrear, permisoLeer))
					.build();

			// creacion de usuarios
			Usuario usuarioJhonatan = Usuario.builder()
					.nombreUsuario("jhona_admin")
					.password(passwordEncoder.encode("jhona_admin"))
					.estaHabilitada(true)
					.cuentaAspirada(true)
					.cuentaBloqueda(true)
					.credencialesExpiradas(true)
					.roles(Set.of(rolAdmin))
					.build();

			Usuario usuarioDaniel = Usuario.builder()
					.nombreUsuario("daniel_usuario")
					.password(passwordEncoder.encode("daniel_usuario"))
					.estaHabilitada(true)
					.cuentaAspirada(true)
					.cuentaBloqueda(true)
					.credencialesExpiradas(true)
					.roles(Set.of(rolUsuario))
					.build();

			Usuario usuarioAndrea = Usuario.builder()
					.nombreUsuario("andrea_invitada")
					.password(passwordEncoder.encode("andrea_invitada"))
					.estaHabilitada(true)
					.cuentaAspirada(true)
					.cuentaBloqueda(true)
					.credencialesExpiradas(true)
					.roles(Set.of(rolInvitado))
					.build();

			Usuario usuarioAnyi = Usuario.builder()
					.nombreUsuario("anyi_desarollador")
					.password(passwordEncoder.encode("anyi_desarollador"))
					.estaHabilitada(true)
					.cuentaAspirada(true)
					.cuentaBloqueda(true)
					.credencialesExpiradas(true)
					.roles(Set.of(rolDesarrollador))
					.build();

			usuarioRepository.saveAll(List.of(usuarioAndrea, usuarioAnyi, usuarioDaniel, usuarioJhonatan));
		};
	}

}
