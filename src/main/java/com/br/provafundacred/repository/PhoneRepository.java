package com.br.provafundacred.repository;

import com.br.provafundacred.entity.Phone;
import com.br.provafundacred.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

}