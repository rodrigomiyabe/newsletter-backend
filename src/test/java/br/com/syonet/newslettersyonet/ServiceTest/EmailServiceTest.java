package br.com.syonet.newslettersyonet.ServiceTest;

import br.com.syonet.newsletters.dtos.ClienteDTO;
import br.com.syonet.newsletters.dtos.NoticiaDTO;
import br.com.syonet.newsletters.services.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendEmail() {
        ClienteDTO cliente = new ClienteDTO();
        cliente.setEmail("cliente@example.com");
        cliente.setNome("João");
        cliente.setDataNascimento(LocalDate.now());

        NoticiaDTO noticia1 = new NoticiaDTO();
        noticia1.setTitulo("Notícia 1");
        noticia1.setDescricao("Descrição 1");
        noticia1.setLink("http://link1.com");

        NoticiaDTO noticia2 = new NoticiaDTO();
        noticia2.setTitulo("Notícia 2");
        noticia2.setDescricao("Descrição 2");

        emailService.sendEmail(cliente, Arrays.asList(noticia1, noticia2));

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());

        SimpleMailMessage message = captor.getValue();
        assertEquals("cliente@example.com", message.getTo()[0]);
        assertEquals("Notícias do dia!", message.getSubject());
        String expectedText = "Bom dia João, Feliz aniversário!\n\nSegue as notícias de hoje.\n\n"
                + "Notícia 1 (http://link1.com)\n"
                + "Descrição 1\n\n"
                + "Notícia 2\n"
                + "Descrição 2\n\n"
                + "Até a próxima.";
        assertEquals(expectedText, message.getText());
    }

    @Test
    public void testSendEmailWithoutBirthday() {
        ClienteDTO cliente = new ClienteDTO();
        cliente.setEmail("cliente@example.com");
        cliente.setNome("João");

        NoticiaDTO noticia = new NoticiaDTO();
        noticia.setTitulo("Notícia 1");
        noticia.setDescricao("Descrição 1");
        noticia.setLink("http://link1.com");

        emailService.sendEmail(cliente, Collections.singletonList(noticia));

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());

        SimpleMailMessage message = captor.getValue();
        assertEquals("cliente@example.com", message.getTo()[0]);
        assertEquals("Notícias do dia!", message.getSubject());
        String expectedText = "Bom dia João\n\nSegue as notícias de hoje.\n\n"
                + "Notícia 1 (http://link1.com)\n"
                + "Descrição 1\n\n"
                + "Até a próxima.";
        assertEquals(expectedText, message.getText());
    }
}

