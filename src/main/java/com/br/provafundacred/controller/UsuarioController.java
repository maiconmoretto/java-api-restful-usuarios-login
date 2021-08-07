package com.br.provafundacred.controller;

import com.br.provafundacred.entity.Phone;
import com.br.provafundacred.entity.Usuario;
import com.br.provafundacred.repository.PhoneRepository;
import com.br.provafundacred.repository.UsuarioRepository;
import com.br.provafundacred.request.LoginRequest;
import com.br.provafundacred.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.List;

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
        if(service.emailExist(request.getEmail())) {
            return new ResponseEntity<String>("E-mail já existente.", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public HttpEntity<? extends Object> login(@RequestHeader(value = "Accept")  String acceptHeader,
                                              @RequestHeader(value = "Authorization")  String authorizationHeader, @RequestBody LoginRequest request) {


       System.out.println("Authorization ============================== "  + authorizationHeader);

       String token = authorizationHeader.substring(7);
               //Bearer c487ea30910a8693ce823b1517994f0c

        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());

        if(!service.emailExist(request.getEmail())) {
            return new ResponseEntity<String>("Usuário e/ou senha inválidos.", HttpStatus.FORBIDDEN);
        }

        if(!service.emailAndPasswordExist(usuario)) {
            return new ResponseEntity<String>("Usuário e/ou senha inválidos.", HttpStatus.FORBIDDEN);
        }

       if(!service.tokenExist(token)) {
           System.out.println("TOKEN NAO EXISTE =================");
            return new ResponseEntity<String>("Não autorizado", HttpStatus.UNAUTHORIZED);
        }

        System.out.println("TOKEN EXISTE =================");
        return new ResponseEntity<Usuario>(service.login(request), HttpStatus.OK);
    }

    @DeleteMapping("/")
    public HttpEntity<? extends Object> deleteAll() {
        service.deleteAll();
        return new ResponseEntity<>("Todos usuários deletados com sucesso.", HttpStatus.OK);
    }

}