package com.br.provafundacred.response;

//import com.br.provafundacred.entity.Phone;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuarios")
public class UsuarioResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String password;
    //private Phone phones;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime last_login;
    private String token;

}