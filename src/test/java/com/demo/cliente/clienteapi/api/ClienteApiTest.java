package com.demo.cliente.clienteapi.api;

import com.demo.cliente.clienteapi.dto.ClienteDto;
import com.demo.cliente.clienteapi.services.ClienteService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClienteApiTest {

    //---[Solo para pruebas sin el API ]----------------
    @Autowired
    private ClienteService clienteService;

    @PersistenceContext
    private EntityManager entityManager;
    //-------------------------------------------------

    private static final String API_ROOT = "http://localhost";
    private static final String API_RESOURCE_ALL = "/v1/api/cliente/all";

    private WebTestClient webTestClient;

    @LocalServerPort
    private int port = 0;

    @BeforeEach
    public void setup() {
        webTestClient = WebTestClient.bindToServer()
                .baseUrl(API_ROOT + ":" + port)
                .responseTimeout(Duration.ofSeconds(3600))
                .build();
    }

    @Test
    void obtenerTodosLosClientes() {
        var clientes = webTestClient
                .get()
                .uri(API_RESOURCE_ALL)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ClienteDto.class)
                .returnResult()
                .getResponseBody();
        assertNotNull(clientes);
        assert clientes.size() > 0;
    }

    @Test
    void obtenerTodosLosClientesV2() {
       var res = clienteService.listarTodosLosClientes();
       System.out.println("");
       //log.info("prueba");
    }


    @Test
    void deasactivarCliente() {
        clienteService.desactivarCliente(1);
        System.out.println("");
        //log.info("prueba");
    }


}