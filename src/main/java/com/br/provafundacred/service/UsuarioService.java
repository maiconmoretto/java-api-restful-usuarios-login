package com.br.provafundacred.service;

import com.br.provafundacred.entity.Usuario;
import com.br.provafundacred.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listAll() {
        return repository.findAll();
    }


    public Usuario create(Usuario Usuario) {
        return repository.save(Usuario);
    }


    public Usuario listById(Long id) {
        return repository.findById(id).get();
    }

    public Usuario updateUsuario(Usuario Usuario) {
        return repository.save(Usuario);
    }

    public void deleteUsuario(Long id) {
        repository.deleteById(id);
    }

}