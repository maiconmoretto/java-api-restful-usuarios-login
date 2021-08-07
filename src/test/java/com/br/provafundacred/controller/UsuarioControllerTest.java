package com.br.provafundacred.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.br.provafundacred.entity.Phone;
import com.br.provafundacred.entity.Usuario;
import com.br.provafundacred.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.internal.jline.internal.Log;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @AfterEach
    public void destroyAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void criarUsuarioSucesso() throws Exception {
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update("123456".getBytes(),0,"123456".length());

        String password = new BigInteger(1,m.digest()).toString(16);

        Usuario usuario = new Usuario();
        usuario.setEmail("joao@joao.com");
        usuario.setName("joao");
        usuario.setPassword(password);
        List<Phone> phoneList = new ArrayList<>();
        Phone phone = new Phone();
        phone.setDdd(051);
        phone.setNumber(999999999);
        phoneList.add(phone);
        usuario.setPhone(phoneList);

        mvc.perform(MockMvcRequestBuilders.post("/").content(asJsonString(usuario))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

    }

    @Test
    public void criarUsuarioDeveRetornarBadRequest() throws Exception {
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

        mvc.perform(MockMvcRequestBuilders.post("/").content(asJsonString(usuario))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.last_login").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.created").exists());
    }

    @Test
    public void loginDeverRetornarForbidden() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("123");
        loginRequest.setPassword("123");

        mvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .content(asJsonString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getUsuario() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void loginDeveRetornarBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/login").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginSucesso() throws Exception {
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update("123456".getBytes(),0,"123456".length());

        String password = new BigInteger(1,m.digest()).toString(16);

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

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("joao@joao.com");
        loginRequest.setPassword(password);

        mvc.perform(MockMvcRequestBuilders.post("/").content(asJsonString(usuario))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.last_login").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.created").exists());


        mvc.perform(MockMvcRequestBuilders.post("/login").content(asJsonString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.last_login").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.created").exists());
    }

    @Test
    public void loginDeveRetornarErroForbidden() throws Exception {
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

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("joao@joao.com");
        loginRequest.setPassword("1234567");

        mvc.perform(MockMvcRequestBuilders.post("/").content(asJsonString(usuario))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.last_login").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.created").exists());


        mvc.perform(MockMvcRequestBuilders.post("/login").content(asJsonString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    public void loginDeveRetornarErroBadRequest() throws Exception {
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

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("joao@jo1ao.com");
        loginRequest.setPassword("123456");

        mvc.perform(MockMvcRequestBuilders.post("/").content(asJsonString(usuario))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.last_login").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.created").exists());


        mvc.perform(MockMvcRequestBuilders.post("/login").content(asJsonString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden()
                );
    }
}