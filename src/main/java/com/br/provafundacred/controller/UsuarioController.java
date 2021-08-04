package com.br.provafundacred.controller;

import com.br.provafundacred.entity.Usuario;
//import com.br.provafundacred.request.UsuarioRequest;
//import com.br.provafundacred.request.UsuarioRequest;
import com.br.provafundacred.request.UsuarioUpdateRequest;
import com.br.provafundacred.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    //@ApiOperation(value = "It will return list of Usuario")
    @GetMapping
    public List<Usuario> viewAllUsuarios() {
        return service.listAll();
    }

   // @ApiOperation(value = "It will add new Usuario")
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario request) {
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