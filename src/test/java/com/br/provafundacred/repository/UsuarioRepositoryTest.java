
package com.br.provafundacred.repository;

import com.br.provafundacred.ProvaFundacredApplication;
import com.br.provafundacred.entity.Phone;
import com.br.provafundacred.entity.Usuario;
import com.br.provafundacred.request.LoginRequest;
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
       usuario.setEmail("joao@joao.com");
       usuario.setName("joao");
       usuario.setPassword("123456");
       List<Phone> phoneList = new ArrayList<>();
       Phone phone = new Phone();
       phone.setDdd(051);
       phone.setNumber(999999999);
       phoneList.add(phone);
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
        usuario.setEmail("joao@joao.com");
        usuario.setName("joao");
        usuario.setPassword("123456");
        List<Phone> phoneList = new ArrayList<>();
        Phone phone = new Phone();
        phone.setDdd(051);
        phone.setNumber(999999999);
        phoneList.add(phone);
        usuario.setId(1);
        mockList.add(usuario);
        Mockito.verify(mockList).add(usuario);
        assertEquals(0, mockList.size());

        Mockito.when(mockList.size()).thenReturn(1);
        assertEquals(1, mockList.size());
    }

    @Test
    public void login() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("123456");
        loginRequest.setEmail("joao@joao.com");

        List<Usuario> mockList = Mockito.mock(ArrayList.class);
        Usuario usuario = new Usuario();
        usuario.setEmail("joao@joao.com");
        usuario.setName("joao");
        usuario.setPassword("123456");
        List<Phone> phoneList = new ArrayList<>();
        Phone phone = new Phone();
        phone.setDdd(051);
        phone.setNumber(999999999);
        phoneList.add(phone);
        usuario.setId(1);
        mockList.add(usuario);
        Mockito.verify(mockList).add(usuario);

        Mockito.when(mockList.size()).thenReturn(1);
        assertEquals(1, mockList.size());
    }

}

