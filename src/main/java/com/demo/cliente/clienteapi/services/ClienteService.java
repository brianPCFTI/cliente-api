package com.demo.cliente.clienteapi.services;

import com.demo.cliente.clienteapi.dto.ClienteDto;
import com.demo.cliente.clienteapi.model.Cliente;
import com.demo.cliente.clienteapi.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDto> listarTodosLosClientes(){
        List<ClienteDto> clienteDtoList = new ArrayList<>();
        clienteRepository
                .findAll()
                .stream()
                .map(cliente -> {
                    clienteDtoList.add(fromClienteToDto(cliente));
                    return cliente;
                })
                .collect(Collectors.toList());
        return clienteDtoList;
    }

    public static ClienteDto fromClienteToDto(Cliente cliente){
        ClienteDto clienteDto = new ClienteDto();
        BeanUtils.copyProperties(cliente, clienteDto);
        return clienteDto;
    }

    public static Cliente toClienteToDto(ClienteDto clienteDto){
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteDto, cliente);
        return cliente;
    }

    public ClienteDto obtenerClientePorIdentificacion(String identificacion) {
        Cliente cliente = clienteRepository.findClienteById(identificacion);
        return fromClienteToDto(cliente);
    }

    public void crearCliente(ClienteDto clienteDto) {
        clienteRepository.save(toClienteToDto(clienteDto));
    }

    public void desactivarCliente(int idCliente) {

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(
                        () -> {
                            throw new RuntimeException("Cliente No Existe");
                        });

        cliente.setEstado(false);
        clienteRepository.save(cliente);
    }

    public void actualizarCliente(ClienteDto clienteDto) {
        Cliente cliente = clienteRepository.findClienteById(Integer.toString(clienteDto.getId()));
        cliente.setEstado(clienteDto.getEstado());
        cliente.setApellidos(clienteDto.getApellidos());
        cliente.setCedula(clienteDto.getCedula());
        cliente.setPaisNacimiento(clienteDto.getPaisNacimiento());
        cliente.setTelefono(clienteDto.getTelefono());
        clienteRepository.save( cliente );
    }

}