package com.br.provafundacred.service;

import com.br.provafundacred.entity.Phone;
import com.br.provafundacred.entity.Usuario;
import com.br.provafundacred.repository.PhoneRepository;
import com.br.provafundacred.repository.UsuarioRepository;
//import com.br.provafundacred.request.UsuarioRequest;
import com.br.provafundacred.request.LoginRequest;
import com.br.provafundacred.request.UsuarioUpdateRequest;
import javassist.bytecode.stackmap.TypeData;
import jdk.internal.jline.internal.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PhoneService phoneService;

    private static final Logger LOG =   LoggerFactory.getLogger(TypeData.ClassName.class);

    public List<Usuario> listAll() {
        List<Usuario> usuarios = new ArrayList<Usuario>();

        repository.findAll().stream().forEach(usuario -> {
            List<Phone> phones = new ArrayList<Phone>();

            usuario.getPhone().stream().forEach(p -> {
                Phone novoPhone = new Phone();
                novoPhone.setId(p.getId());
                novoPhone.setNumber(p.getNumber());
                novoPhone.setDdd(p.getDdd());
                phones.add(novoPhone);
            });
            Usuario novoUsuario = new Usuario();
            usuario.setId(usuario.getId());
            usuario.setName(usuario.getName());
            usuario.setEmail(usuario.getEmail());
            usuario.setPhone(phones);
            usuarios.add(usuario);
        });

        return usuarios;
    }

    public Usuario create(Usuario request) throws Exception {
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(request.getPassword().getBytes(),0,request.getPassword().length());

        String password = new BigInteger(1,m.digest()).toString(16);
        System.out.println("MD5: "+ password);
       // Log.info("MD " + password);

        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setName(request.getName());
        usuario.setCreated(LocalDateTime.now());
        usuario.setLast_login(LocalDateTime.now());
        List<Phone> phoneList = new ArrayList<Phone>();

        request.getPhone().stream().forEach(p -> {
            Phone phone = new Phone();
            phone.setId(p.getId());
            phone.setDdd(p.getDdd());
            phone.setNumber(p.getNumber());
            phoneList.add(phone);
            phoneService.create(phone);
        });

        usuario.setToken(UUID.randomUUID());
        usuario.setPhone(phoneList);
        usuario.setPassword(password);

        return repository.save(usuario);
    }

    public Usuario login(LoginRequest request) {
        return  repository.findByEmailSenha(request.getEmail(), request.getPassword());
    }


    public boolean emailAndPasswordExist(Usuario request) {
        Usuario usuarioExiste = repository.findByEmailSenha(request.getEmail(), request.getPassword());
        return usuarioExiste == null ? false : true;
    }

    public boolean emailExist(String email) {
        Usuario usuarioExiste =  repository.findByEmail(email);
        return usuarioExiste == null ? false : true;

    }

    public void deleteAll() {
        repository.deleteAll();
    }
}