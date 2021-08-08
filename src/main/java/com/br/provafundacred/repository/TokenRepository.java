package com.br.provafundacred.repository;

import com.br.provafundacred.entity.Token;
import com.br.provafundacred.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Long> {


    @Query("select t from Token t where t.token = ?1")
    Token findByToken(String token);
}