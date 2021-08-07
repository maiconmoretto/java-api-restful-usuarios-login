
package com.br.provafundacred.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import com.br.provafundacred.repository.UsuarioRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.br.provafundacred.entity.Phone;
import com.br.provafundacred.entity.Usuario;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    @Autowired
    @InjectMocks
    private UsuarioService usuarioService;
    private Usuario usuario1;
    private Usuario usuario2;
    List<Usuario> usuarioList;

    @BeforeEach
    public void setUp() {
        usuarioList = new ArrayList<>();
        Usuario usuario = new Usuario();
        usuario.setEmail("joao@joao.com");
        usuario.setName("joao");
        usuario.setPassword("123456");
        List<Phone> phoneList = new ArrayList<>();
        Phone phone = new Phone();
        phone.setDdd(051);
        phone.setNumber(999999999);
        phoneList.add(phone);
        usuario.setPhone(phoneList);

        usuarioList.add(usuario);
    }

    @AfterEach
    public void tearDown() {
        usuario1 = usuario2 = null;
        usuarioList = null;
    }

    @Test
    public void createUsuario() throws Exception {
        when(usuarioRepository.save(usuario1)).thenReturn(usuario1);
        usuarioService.create(usuario1);
        verify(usuarioRepository,times(1)).save(usuario1);
    }

    @Test
    public void findAll() throws Exception {
        when(usuarioRepository.findAll()).thenReturn(usuarioList);
        usuarioService.listAll();
        verify(usuarioRepository,times(1)).findAll();
    }


}


