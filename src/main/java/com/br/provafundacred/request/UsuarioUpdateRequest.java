package com.br.provafundacred.request;

//import com.br.provafundacred.entity.Phone;
import lombok.Data;

@Data
public class UsuarioUpdateRequest {

    private Integer id;
    private String name;
    private String email;
    private String password;
    //private Phone phones;

}