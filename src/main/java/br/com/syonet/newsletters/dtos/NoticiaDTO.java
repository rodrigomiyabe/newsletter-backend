package br.com.syonet.newsletters.dtos;

import br.com.syonet.newsletters.entities.Noticia;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticiaDTO implements Serializable {
    private Long idNoticia;
    @NotBlank(message = "Título da notícia não pode ser nulo!")
    private String titulo;
    @NotBlank(message = "Descrição da notícia não pode ser nulo!")
    private String descricao;
    @NotBlank(message = "Link da notícia não pode ser nulo!")
    private String link;

    public NoticiaDTO(Noticia noticia) {
        this.idNoticia = noticia.getId();
        this.titulo = noticia.getTitulo();
        this.descricao = noticia.getDescricao();
        this.link = noticia.getLink();
    }
}
