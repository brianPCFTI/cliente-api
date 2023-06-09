package com.demo.cliente.clienteapi.api;

import com.demo.cliente.clienteapi.dto.ClienteDto;
import com.demo.cliente.clienteapi.services.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/api/cliente")
@Slf4j
public class ClienteApi {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/all")
    public List<ClienteDto> obtenerTodosLosClientes(){

        //List<ClienteDto> clientes = new ArrayList<>();
        //return clientes;
        return clienteService.listarTodosLosClientes();
    }

    @GetMapping("/all2")
    public List<ClienteDto> obtenerTodosLosClientesV2(){

        List<ClienteDto> clientes = new ArrayList<>();
        return clientes;
        //return clienteService.listarTodosLosClientes();
    }

    @GetMapping("/parameter")
    public ClienteDto buscarClientes(@RequestParam String identificacion) {
        return clienteService.obtenerClientePorIdentificacion(identificacion);
    }

    @PostMapping
    public void crearCliente(@RequestBody ClienteDto clienteDto) {
        log.info("Cliente a Agregar: {}", clienteDto);
        clienteService.crearCliente(clienteDto);
    }

    @PutMapping(value = "/deasactivarCliente")
    public void deasactivarCliente(@RequestBody ClienteDto clienteDto) {
        log.info("Cliente a Desactivar: {}", clienteDto);
        clienteService.desactivarCliente(clienteDto.getId());
    }

    @PutMapping
    public void actualizarCliente(@RequestBody ClienteDto clienteDto) {
        log.info("Cliente a Actualizar: {}", clienteDto);
        clienteService.actualizarCliente(clienteDto);
    }

}
