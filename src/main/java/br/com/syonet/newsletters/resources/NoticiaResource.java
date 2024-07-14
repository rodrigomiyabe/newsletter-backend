package br.com.syonet.newsletters.resources;

import br.com.syonet.newsletters.dtos.NoticiaDTO;
import br.com.syonet.newsletters.services.NoticiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public class NoticiaResource {

    private final NoticiaService service;

    public NoticiaResource(NoticiaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<NoticiaDTO> cadastrarNoticia(NoticiaDTO dto) {
        return ResponseEntity.ok().body(this.service.cadastroNoticia(dto));
    }
}
