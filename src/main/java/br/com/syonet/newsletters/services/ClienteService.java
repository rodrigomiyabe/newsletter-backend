package br.com.syonet.newsletters.services;

import br.com.syonet.newsletters.dtos.ClienteDTO;
import br.com.syonet.newsletters.entities.Cliente;
import br.com.syonet.newsletters.repository.ClienteRepository;
import br.com.syonet.newsletters.services.exceptions.EmailJaExistenteException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteDTO cadastroCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        if (listaClientePorEmail(clienteDTO.getEmail()).isEmpty()) {
            cliente.setEmail(clienteDTO.getEmail());
        }else{
            throw new EmailJaExistenteException("E-mail já cadastrado!");
        }
        if(clienteDTO.getDataNascimento() != null) {
            cliente.setDataNascimento(LocalDate.parse(clienteDTO.getDataNascimento().format(DateTimeFormatter.ISO_DATE)));
        } else {
            cliente.setDataNascimento(null);
        }
        return new ClienteDTO(repository.save(cliente));
    }

    public List<ClienteDTO> listaClientes() {
        List<Cliente> clientes = repository.findAll();
        return clientes.stream().map(ClienteDTO::new).toList();
    }

    public Optional<ClienteDTO> listaClientePorEmail(String email) {
        Optional<Cliente> cliente = this.repository.findByEmail(email);
        return cliente.map(ClienteDTO::new);
    }
}
