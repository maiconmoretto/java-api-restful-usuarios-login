package com.br.provafundacred.controller;

import com.br.provafundacred.entity.Phone;
import com.br.provafundacred.entity.Usuario;
import com.br.provafundacred.repository.PhoneRepository;
import com.br.provafundacred.repository.UsuarioRepository;
import com.br.provafundacred.request.LoginRequest;
import com.br.provafundacred.service.TokenService;
import com.br.provafundacred.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //@ApiOperation(value = "It will return list of Usuario")
    @GetMapping
    public List<Usuario> listAll() {
        return usuarioService.listAll();
    }


    // @ApiOperation(value = "It will add new Usuario")
    @PostMapping
    public HttpEntity<? extends Object> create(@RequestBody Usuario request) throws Exception {
        if (usuarioService.emailExist(request.getEmail())) {
            return new ResponseEntity<String>("E-mail já existente.", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(usuarioService.create(request), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public HttpEntity<? extends Object> login(@RequestBody LoginRequest request) {
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());

        if (!usuarioService.emailExist(request.getEmail())) {
            return new ResponseEntity<String>("Usuário e/ou senha inválidos.", HttpStatus.FORBIDDEN);
        }

        if (!usuarioService.emailAndPasswordExist(usuario)) {
            return new ResponseEntity<String>("Usuário e/ou senha inválidos.", HttpStatus.FORBIDDEN);
        }

        Usuario usuarioUpdate = usuarioService.findByEmailAndPassword(request.getEmail(), request.getPassword());

        usuarioService.updateLoginTime(usuarioUpdate);

        return new ResponseEntity<Usuario>(usuarioService.login(request), HttpStatus.OK);
    }

    @GetMapping("/perfil/{id}")
    public HttpEntity<? extends Object> perfil(@RequestHeader(value = "Accept") String acceptHeader,
                                               @RequestHeader(value = "Authorization") String authorizationHeader,
                                               @PathVariable Integer id) throws Exception {
        String token = authorizationHeader.substring(7);
        Optional<Usuario> usuario = null;

        if (!tokenService.tokenExist(token)) {
            return new ResponseEntity<String>("Não autorizado", HttpStatus.UNAUTHORIZED);
        }

        try {
            usuario = usuarioService.findById(id);

            if (usuario == null) {
                return new ResponseEntity<String>("Não autorizado.", HttpStatus.FORBIDDEN);
            }

            if (!usuario.get().getToken().getToken().equalsIgnoreCase(token)) {
                return new ResponseEntity<String>("Não autorizado.", HttpStatus.FORBIDDEN);
            }

            if (LocalDateTime.now().minusMinutes(30).isBefore(usuario.get().getLast_login())) {
                return new ResponseEntity<String>("Sessão inválida.", HttpStatus.FORBIDDEN);
            }

        } catch (Exception e) {
            throw new Exception("Exception: " + e.getMessage());
        }

        return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
    }


    @DeleteMapping("/")
    public HttpEntity<? extends Object> deleteAll() {
        usuarioService.deleteAll();
        return new ResponseEntity<>("Todos usuários deletados com sucesso.", HttpStatus.OK);
    }

}