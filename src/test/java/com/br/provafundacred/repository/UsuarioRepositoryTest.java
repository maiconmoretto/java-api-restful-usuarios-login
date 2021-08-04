package com.br.provafundacred.repository;

import com.br.provafundacred.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

/*    @Test
    public void whenFindingUsuarioById_thenCorrect() {
        usuarioRepository.save(new Usuario());
       // assert(usuarioRepository.findById(1L)).isInstanceOf(Optional.class);
    }

    @Test
    public void whenFindingAllUsuarios_thenCorrect() {
        usuarioRepository.save(new Usuario());
        usuarioRepository.save(new Usuario());
        //assert(usuarioRepository.findAll()).isInstanceOf(List.class);
    }


    @Test
    public void whenSavingUsuario_thenCorrect() {
        usuarioRepository.save(new Usuario());
        Usuario Usuario = usuarioRepository.findById(1L).orElseGet(()
                -> new Usuario());
        assert(Usuario.getName()).equalsIgnoreCase("Bob");
    }*/

}
