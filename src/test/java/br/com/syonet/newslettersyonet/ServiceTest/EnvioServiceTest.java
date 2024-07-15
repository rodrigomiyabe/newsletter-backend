package br.com.syonet.newslettersyonet.ServiceTest;

import br.com.syonet.newsletters.dtos.ClienteDTO;
import br.com.syonet.newsletters.dtos.NoticiaDTO;
import br.com.syonet.newsletters.services.ClienteService;
import br.com.syonet.newsletters.services.EmailService;
import br.com.syonet.newsletters.services.EnvioService;
import br.com.syonet.newsletters.services.NoticiaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class EnvioServiceTest {

    @Mock
    private ClienteService clienteService;

    @Mock
    private NoticiaService noticiaService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private EnvioService envioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendDailyNewsletters() {
        // Mock data
        ClienteDTO cliente1 = new ClienteDTO();
        cliente1.setNome("John Doe");
        cliente1.setEmail("john.doe@example.com");

        ClienteDTO cliente2 = new ClienteDTO();
        cliente2.setNome("Jane Doe");
        cliente2.setEmail("jane.doe@example.com");

        NoticiaDTO noticia1 = new NoticiaDTO();
        noticia1.setTitulo("Noticia 1");
        noticia1.setDescricao("Descricao 1");
        noticia1.setLink("http://example.com/1");

        NoticiaDTO noticia2 = new NoticiaDTO();
        noticia2.setTitulo("Noticia 2");
        noticia2.setDescricao("Descricao 2");
        noticia2.setLink("http://example.com/2");

        List<ClienteDTO> clientes = Arrays.asList(cliente1, cliente2);
        List<NoticiaDTO> noticias = Arrays.asList(noticia1, noticia2);

        // Mock behaviors
        when(clienteService.listaClientes()).thenReturn(clientes);
        when(noticiaService.listarNoticiasNaoProcessadas()).thenReturn(noticias);

        // Call the method to test
        envioService.sendDailyNewsletters();

        // Verify interactions
        verify(clienteService, times(1)).listaClientes();
        verify(noticiaService, times(1)).listarNoticiasNaoProcessadas();

        for (ClienteDTO cliente : clientes) {
            verify(emailService, times(1)).sendEmail(cliente, noticias);
        }

        for (NoticiaDTO noticia : noticias) {
            verify(noticiaService, times(1)).processaNoticia(noticia);
        }
    }
}

