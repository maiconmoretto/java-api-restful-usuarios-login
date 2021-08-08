package com.br.provafundacred.service;

import com.br.provafundacred.entity.Phone;
import com.br.provafundacred.entity.Token;
import com.br.provafundacred.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public Token create(Token token) {
        return tokenRepository.save(token);
    }


    public boolean tokenExist(String token) {
        return tokenRepository.findByToken(token) == null ? false : true;
    }

}