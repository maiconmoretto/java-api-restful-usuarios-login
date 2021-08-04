package com.br.provafundacred.service;

import com.br.provafundacred.entity.Phone;
import com.br.provafundacred.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    public Phone create(Phone phone) {
        return phoneRepository.save(phone);
    }


}