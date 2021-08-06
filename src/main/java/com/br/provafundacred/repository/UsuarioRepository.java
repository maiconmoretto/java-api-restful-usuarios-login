package com.br.provafundacred.repository;

import com.br.provafundacred.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    @Query("select u from Usuario u where u.email = ?1 and password = ?2")
    Usuario findByEmailSenha(String email, String password);
}