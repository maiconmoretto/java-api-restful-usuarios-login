package com.br.provafundacred.entity;

import javax.persistence.*;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer number;
    private Integer ddd;

    @ManyToOne
    private Usuario usuario;

}