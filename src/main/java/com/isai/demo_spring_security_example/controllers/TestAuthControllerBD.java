package com.isai.demo_spring_security_example.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth-bd")
@PreAuthorize("denyAll()")
public class TestAuthControllerBD {

    @GetMapping(path = "/get")
    @PreAuthorize("hasAnyAuthority('READ')")
    public String holaGet() {
        return "Hola Mundo -GET";
    }

    @PostMapping(path = "/post")
    @PreAuthorize("hasAnyAuthority('CREATE') or hasAnyAuthority('READ')  ")
    public String holaPost() {
        return "Hola Mundo -POST";
    }

    @PutMapping(path = "/put")
    public String holaPut() {
        return "Hola Mundo -PUT";
    }

    @DeleteMapping(path = "/delete")
    public String holaDelete() {
        return "Hola Mundo -DELETE";
    }

    @PatchMapping(path = "/patch")
    @PreAuthorize("hasAnyAuthority('REFACTOR')")
    public String holaPatch() {
        return "Hola Mundo -PATCH";
    }

}
