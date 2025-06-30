package com.isai.demo_spring_security_example.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class TestAuthController {

    @RequestMapping(path = "/hola", method = RequestMethod.GET)
    public String holaMundo() {
        return "Hola Mundo";
    }

    @RequestMapping(path = "/hola-seguro", method = RequestMethod.GET)
    public String holaMundoSeguro() {
        return "Hola Mundo";
    }

}
