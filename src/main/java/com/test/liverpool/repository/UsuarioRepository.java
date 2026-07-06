package com.test.rupper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.rupper.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
