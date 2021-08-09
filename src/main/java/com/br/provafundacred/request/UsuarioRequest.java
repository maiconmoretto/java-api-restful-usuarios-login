package com.br.provafundacred.request;

import com.br.provafundacred.entity.Phone;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;


@Data
public class UsuarioRequest {

    private String name;
    private String email;
    private String password;


    @OneToMany(mappedBy = "usuario")
    private List<Phone> phone;

}
