package com.br.provafundacred.entity;

import javax.persistence.*;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime modified;
    private LocalDateTime last_login;
    private String token;

    @OneToMany
    private List<Phone> phone;

}

