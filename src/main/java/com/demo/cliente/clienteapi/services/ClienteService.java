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

    /*
    public ClienteDto obtenerClientePorIdentificacion(String identificacion) {
        Cliente cliente = clienteRepository.findClienteByIdentificacion(identificacion);

        return fromClienteToDto(cliente);
    }

    public void desactivarCliente(int idCliente) {

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(
                        () -> {
                            throw new RuntimeException("Cliente No Existe");
                        });

        cliente.setEstado(false);
    }



    public void crearCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        clienteRepository.save(cliente);
    }

    public void actualizarCliente(ClienteDto clienteDto) {

        //Cliente cliente = clienteRepository.findClienteById(clienteDto.getId());
        //clienteRepository.save(cliente);

    }
*/

}
