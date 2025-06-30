package com.isai.demo_spring_security_example.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
@PreAuthorize("denyAll()")
public class TestAuthController {

    @RequestMapping(path = "/hola", method = RequestMethod.GET)
    @PreAuthorize("permitAll()")
    public String holaMundo() {
        return "Hola Mundo";
    }

    @RequestMapping(path = "/hola-seguro", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('READ')")
    public String holaMundoSeguro() {
        return "Hola Mundo";
    }
    
    @PreAuthorize("hasAnyAuthority('CREATED')")
    @RequestMapping(path = "/hola-seguro2", method = RequestMethod.GET)
    public String holaMundoSeguro2() {
        return "Hola Mundo";
    }

}
