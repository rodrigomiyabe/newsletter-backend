package br.com.syonet.newslettersyonet.ServiceTest;

import br.com.syonet.newsletters.dtos.NoticiaDTO;
import br.com.syonet.newsletters.entities.Noticia;
import br.com.syonet.newsletters.repository.NoticiaRepository;
import br.com.syonet.newsletters.services.NoticiaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NoticiaServiceTest {

    @Mock
    private NoticiaRepository repository;

    @InjectMocks
    private NoticiaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cadastroNoticia() {
        NoticiaDTO dto = new NoticiaDTO();
        dto.setTitulo("Titulo");
        dto.setDescricao("Descricao");
        dto.setLink("http://example.com");

        Noticia noticia = new Noticia();
        noticia.setTitulo(dto.getTitulo());
        noticia.setDescricao(dto.getDescricao());
        noticia.setLink(dto.getLink());

        when(repository.save(any(Noticia.class))).thenReturn(noticia);

        NoticiaDTO result = service.cadastroNoticia(dto);

        assertNotNull(result);
        assertEquals("Titulo", result.getTitulo());
        assertEquals("Descricao", result.getDescricao());
        assertEquals("http://example.com", result.getLink());

        verify(repository, times(1)).save(any(Noticia.class));
    }

    @Test
    void listarNoticiasNaoProcessadas() {
        Noticia noticia1 = new Noticia();
        noticia1.setTitulo("Titulo 1");
        noticia1.setDescricao("Descricao 1");
        noticia1.setLink("http://example.com/1");
        noticia1.setProcessada("N");

        Noticia noticia2 = new Noticia();
        noticia2.setTitulo("Titulo 2");
        noticia2.setDescricao("Descricao 2");
        noticia2.setLink("http://example.com/2");
        noticia2.setProcessada("N");

        when(repository.findNoticiaByProcessadaIs("N")).thenReturn(Arrays.asList(noticia1, noticia2));

        List<NoticiaDTO> result = service.listarNoticiasNaoProcessadas();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Titulo 1", result.get(0).getTitulo());
        assertEquals("Titulo 2", result.get(1).getTitulo());

        verify(repository, times(1)).findNoticiaByProcessadaIs("N");
    }

    @Test
    void processaNoticia() {
        NoticiaDTO dto = new NoticiaDTO();
        dto.setIdNoticia(1L);

        Noticia noticia = new Noticia();
        noticia.setId(1L);
        noticia.setTitulo("Titulo");
        noticia.setDescricao("Descricao");
        noticia.setLink("http://example.com");
        noticia.setProcessada("N");

        when(repository.findById(1L)).thenReturn(Optional.of(noticia));
        when(repository.save(any(Noticia.class))).thenReturn(noticia);

        service.processaNoticia(dto);

        assertEquals("S", noticia.getProcessada());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(noticia);
    }
}

