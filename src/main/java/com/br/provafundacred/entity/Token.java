package com.br.provafundacred.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String token;
    private LocalDateTime created;

    @OneToOne
    private Usuario usuario;

}