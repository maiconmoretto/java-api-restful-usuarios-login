package com.br.provafundacred.controller;

import com.br.provafundacred.entity.Phone;
import com.br.provafundacred.entity.Usuario;
//import com.br.provafundacred.request.UsuarioRequest;
//import com.br.provafundacred.request.UsuarioRequest;
import com.br.provafundacred.repository.PhoneRepository;
import com.br.provafundacred.repository.UsuarioRepository;
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
        List<Usuario> usuarioExiste = usuarioRepository.findAll().stream().filter(u -> u.getEmail().equalsIgnoreCase(request.getEmail())).collect(Collectors.toList());
        if(usuarioExiste.size() > 0) {

            return new ResponseEntity<String>("E-mail já existente.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

/*
    @GetMapping("/{id}")
    public Usuario listById(@PathVariable Long id) {
        return service.listById(id);
    }

    //@ApiOperation(value = "It will update Usuario")
    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Integer id, @RequestBody UsuarioUpdateRequest request) {
        UsuarioUpdateRequest usuarioUpdateRequest = new UsuarioUpdateRequest();
        usuarioUpdateRequest.setId(id);
        return service.updateUsuario(usuarioUpdateRequest);
    }

    //@ApiOperation(value = "It will delete  Usuario")
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        service.deleteUsuario(id);
    }*/

}