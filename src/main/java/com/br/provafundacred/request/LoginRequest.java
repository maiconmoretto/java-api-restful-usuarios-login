package com.br.provafundacred.request;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class LoginRequest {

    private String email;
    private String password;

}

