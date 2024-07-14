package br.com.syonet.newsletters.repository;


import br.com.syonet.newsletters.entities.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {


    List<Noticia> findNoticiaByProcessadaIs(String processada);
}
