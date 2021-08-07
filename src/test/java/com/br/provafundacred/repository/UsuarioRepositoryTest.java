
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

   @Test
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
    }

    @Test
    public void criarUsuario() {
        List<Usuario> mockList = Mockito.mock(ArrayList.class);
        Usuario usuario = new Usuario();
        usuario.setName("name a");
        usuario.setEmail("setEmail a");
        usuario.setPassword("password a");
        usuario.setId(1);
        mockList.add(usuario);
        Mockito.verify(mockList).add(usuario);

        Mockito.when(mockList.size()).thenReturn(1);
        assertEquals(1, mockList.size());
    }




}

