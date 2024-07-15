package br.com.syonet.newsletters.services;

import br.com.syonet.newsletters.dtos.ClienteDTO;
import br.com.syonet.newsletters.dtos.NoticiaDTO;
import br.com.syonet.newsletters.services.exceptions.ListaVaziaException;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class EnvioService {

    private final ClienteService clienteService;
    private final NoticiaService noticiaService;
    private final EmailService emailService;

    public EnvioService(ClienteService clienteService, NoticiaService noticiaService, EmailService emailService) {
        this.clienteService = clienteService;
        this.noticiaService = noticiaService;
        this.emailService = emailService;
    }


    @Scheduled(cron = "0 8 * * *")
    public void sendDailyNewsletters() {
        List<ClienteDTO> clientes = clienteService.listaClientes();
        List<NoticiaDTO> noticias = noticiaService.listarNoticiasNaoProcessadas();
        if (!clientes.isEmpty() && !noticias.isEmpty()) {
            for (ClienteDTO cliente : clientes) {
                emailService.sendEmail(cliente, noticias);
            }

            for (NoticiaDTO noticia : noticias) {
                noticiaService.processaNoticia(noticia);
            }
        } else throw new ListaVaziaException("Lista de cliente ou noticia vazia!");

    }
}
