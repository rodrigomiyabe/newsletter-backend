package br.com.syonet.newslettersyonet.ServiceTest;

import br.com.syonet.newsletters.dtos.ClienteDTO;
import br.com.syonet.newsletters.entities.Cliente;
import br.com.syonet.newsletters.repository.ClienteRepository;
import br.com.syonet.newsletters.services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cadastroCliente() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("John Doe");
        clienteDTO.setEmail("john.doe@example.com");
        clienteDTO.setDataNascimento(LocalDate.of(1990, 1, 1));

        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setDataNascimento(clienteDTO.getDataNascimento());

        when(repository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteDTO result = service.cadastroCliente(clienteDTO);

        assertNotNull(result);
        assertEquals("John Doe", result.getNome());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals(LocalDate.of(1990, 1, 1), result.getDataNascimento());

        verify(repository, times(1)).save(any(Cliente.class));
    }

    @Test
    void listaClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setNome("John Doe");
        cliente1.setEmail("john.doe@example.com");
        cliente1.setDataNascimento(LocalDate.of(1990, 1, 1));

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Jane Doe");
        cliente2.setEmail("jane.doe@example.com");
        cliente2.setDataNascimento(LocalDate.of(1992, 2, 2));

        when(repository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        List<ClienteDTO> result = service.listaClientes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getNome());
        assertEquals("Jane Doe", result.get(1).getNome());

        verify(repository, times(1)).findAll();
    }
}
