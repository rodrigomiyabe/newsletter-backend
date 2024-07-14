package br.com.syonet.newsletters.services;

import br.com.syonet.newsletters.dtos.ClienteDTO;
import br.com.syonet.newsletters.entities.Cliente;
import br.com.syonet.newsletters.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteDTO cadastroCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setDataNascimento(clienteDTO.getDataNascimento());
        return new ClienteDTO(repository.save(cliente));
    }

    public List<ClienteDTO> listaClientes() {
        List<Cliente> clientes = repository.findAll();
        return clientes.stream().map(ClienteDTO::new).toList();
    }
}
