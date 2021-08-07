package com.br.provafundacred.controller;

import com.br.provafundacred.entity.Phone;
import com.br.provafundacred.entity.Usuario;
//import com.br.provafundacred.request.UsuarioRequest;
//import com.br.provafundacred.request.UsuarioRequest;
import com.br.provafundacred.repository.PhoneRepository;
import com.br.provafundacred.repository.UsuarioRepository;
import com.br.provafundacred.request.LoginRequest;
import com.br.provafundacred.request.UsuarioUpdateRequest;
import com.br.provafundacred.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //@ApiOperation(value = "It will return list of Usuario")
    @GetMapping
    public List<Usuario> listAll() {
        return service.listAll();
    }

    @GetMapping("/list-all-phones")
    public List<Phone> listAllPhones() {
        return phoneRepository.findAll();
    }

   // @ApiOperation(value = "It will add new Usuario")
    @PostMapping
    public HttpEntity<? extends Object> create(@RequestBody Usuario request) throws Exception {
        if(service.emailAndPasswordExist(request)) {
            return new ResponseEntity<String>("E-mail já existente.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public HttpEntity<? extends Object> login(@RequestBody LoginRequest request) {
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());

        if(!service.emailExist(request.getEmail())) {
            return new ResponseEntity<String>("Usuário e/ou senha inválidos.", HttpStatus.UNAUTHORIZED);
        }

        if(!service.emailAndPasswordExist(usuario)) {
            return new ResponseEntity<String>("Usuário e/ou senha inválidos.", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<Usuario>(service.login(request), HttpStatus.OK);
    }

    @DeleteMapping("/")
    public HttpEntity<? extends Object> deleteAll() {
        service.deleteAll();
        return new ResponseEntity<>("Todos usuários deletados com sucesso.", HttpStatus.OK);
    }

}