package com.demo.cliente.clienteapi.repository;

import com.demo.cliente.clienteapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>, JpaSpecificationExecutor<Cliente> {

    List<Cliente> findAll();

    Cliente findClienteById(String identificacion);

}