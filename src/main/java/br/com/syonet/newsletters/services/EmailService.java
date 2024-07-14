package br.com.syonet.newsletters.services;

import br.com.syonet.newsletters.dtos.ClienteDTO;
import br.com.syonet.newsletters.dtos.NoticiaDTO;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    public void sendEmail(ClienteDTO cliente, List<NoticiaDTO> noticias) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(cliente.getEmail());
        message.setSubject("Notícias do dia!");
        StringBuilder sb = new StringBuilder();
        sb.append("Bom dia ").append(cliente.getNome());
        if (cliente.getDataNascimento() != null && cliente.getDataNascimento().equals(LocalDate.now())) {
            sb.append(", Feliz aniversário!");
        }
        sb.append("\n\nSegue as notícias de hoje.\n\n");

        for (NoticiaDTO noticia : noticias) {
            if (noticia.getLink() != null && !noticia.getLink().isEmpty()) {
                sb.append(noticia.getTitulo()).append(" (").append(noticia.getLink()).append(")\n");
            } else {
                sb.append(noticia.getTitulo()).append("\n");
            }
            sb.append(noticia.getDescricao()).append("\n\n");
        }
        sb.append("Até a próxima.");

        message.setText(sb.toString());
        mailSender.send(message);
    }
}
