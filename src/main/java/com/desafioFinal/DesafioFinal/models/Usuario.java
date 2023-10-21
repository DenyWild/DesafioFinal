package com.desafioFinal.DesafioFinal.models;

import com.desafioFinal.DesafioFinal.models.enums.Role;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String cpf;
    private Role role;

}
