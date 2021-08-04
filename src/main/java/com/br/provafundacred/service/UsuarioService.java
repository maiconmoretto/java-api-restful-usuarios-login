package com.br.provafundacred.service;

import com.br.provafundacred.entity.Phone;
import com.br.provafundacred.entity.Usuario;
import com.br.provafundacred.repository.UsuarioRepository;
import com.br.provafundacred.request.UsuarioRequest;
import com.br.provafundacred.request.UsuarioUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class UsuarioService {

  @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listAll() {
        return repository.findAll();
    }


    public Usuario create(Usuario request) {
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setName(request.getName());
        List<Phone> phoneList = new ArrayList<Phone>();

        request.getPhone().stream().forEach(p -> {
            Phone phone= new Phone();
            phone.setDdd(p.getDdd());
            phone.setNumber(p.getNumber());
            phoneList.add(phone);
        });

        usuario.setPhone(phoneList);
        usuario.setPassword(request.getPassword());

        return repository.save(usuario);
    }

 /*
    public Usuario listById(Long id) {
        return repository.findById(id).get();
    }

    public Usuario updateUsuario(UsuarioUpdateRequest request) {
        Usuario usuario = new Usuario();
        usuario.setModified(LocalDateTime.now());
        usuario.setEmail(request.getEmail());
        usuario.setId(request.getId());
        usuario.setName(request.getName());
        usuario.setPhones(request.getPhones());
        usuario.setPassword(request.getPassword());

        return repository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        repository.deleteById(id);
    }*/

}