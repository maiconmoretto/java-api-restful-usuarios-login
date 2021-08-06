/*
package com.br.provafundacred.repository;

import com.br.provafundacred.ProvaFundacredApplication;
import com.br.provafundacred.entity.Usuario;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProvaFundacredApplication.class)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    private Usuario usuario;

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

*/
/*    @Test
    public void findAll() {
        List<Usuario> mockList = Mockito.mock(ArrayList.class);
        Usuario usuario = new Usuario();
        usuario.setName("name a");
        usuario.setEmail("setEmail a");
        usuario.setPassword("password a");
        usuario.setId(1);
        mockList.add(usuario);
        Mockito.verify(mockList).add(usuario);
        assertEquals(0, mockList.size());

        Mockito.when(mockList.size()).thenReturn(100);
        assertEquals(100, mockList.size());
    }*//*

*/
/*
    @Test
    public void findAll() {
        Usuario usuario = new Usuario();
        usuario.setName("name a");
        usuario.setEmail("setEmail a");
        usuario.setPassword("password a");
        usuario.setId(1);

        List<Usuario> usuarioList = new ArrayList<Usuario>();
        usuarioList.add(usuario);
        //when(repository.findAll()).thenReturn(usuarioList);
        Mockito.when(repository.findAll()).thenReturn(usuarioList);
        List<Usuario> result = repository.findAll();
        assertEquals(1,1);
        //assertEquals(3, result.size());
    }

    @Test
    public void deleteById() {
        repository.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void save() {
        Usuario usuario = new Usuario();
        usuario.setName("name a");
        usuario.setEmail("setEmail a");
        usuario.setPassword("password a");
        usuario.setId(1);
        when(repository.save(usuario)).thenReturn(usuario);
        Usuario result = repository.save(usuario);
        assertEquals("name a", result.getName());
    }

    @Test
    public void update() {
        Usuario usuario = new Usuario();
        usuario.setName("name a");
        usuario.setEmail("setEmail a");
        usuario.setPassword("password a");
        usuario.setId(1);
        when(repository.save(usuario)).thenReturn(usuario);
        Usuario result = repository.save(usuario);
        assertEquals("name a", result.getName());
    }

    @Test
    public void findById() {
        Optional<Usuario> category = Optional.of(new Usuario());
        when(repository.findById(1L)).thenReturn(category);
        Optional<Usuario> result = repository.findById(1L);
        assertEquals("name a", result.get().getName());
    }*//*


}
*/
