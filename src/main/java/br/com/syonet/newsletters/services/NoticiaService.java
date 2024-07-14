package br.com.syonet.newsletters.services;

import br.com.syonet.newsletters.dtos.NoticiaDTO;
import br.com.syonet.newsletters.entities.Noticia;
import br.com.syonet.newsletters.repository.NoticiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticiaService {

    private final NoticiaRepository repository;

    public NoticiaService(NoticiaRepository repository) {
        this.repository = repository;
    }

    public NoticiaDTO cadastroNoticia(NoticiaDTO dto) {
        Noticia noticia = new Noticia();
        noticia.setTitulo(dto.getTitulo());
        noticia.setDescricao(dto.getDescricao());
        noticia.setLink(dto.getLink());
        return new NoticiaDTO(this.repository.save(noticia));
    }

    public List<NoticiaDTO> listarNoticiasNaoProcessadas() {
        List<Noticia> noticiaList = this.repository.findNoticiaByProcessadaIs("N");
        return noticiaList.stream().map(NoticiaDTO::new).toList();
    }

    public void processaNoticia(NoticiaDTO dto) {
        Noticia noticia = this.repository.findById(dto.getIdNoticia()).orElseThrow(()-> new RuntimeException(""));
        noticia.setProcessada("S");
        repository.save(noticia);
    }
}
