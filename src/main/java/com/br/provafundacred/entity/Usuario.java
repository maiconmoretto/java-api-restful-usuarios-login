package com.br.provafundacred.entity;

import javax.persistence.*;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private UUID token;

    @OneToMany
    private List<Phone> phone;

}

