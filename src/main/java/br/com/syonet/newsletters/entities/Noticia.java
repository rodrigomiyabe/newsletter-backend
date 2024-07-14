package br.com.syonet.newsletters.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Noticia")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Noticia implements Serializable {

    @Id
    private Long id;
    @Column(name="titulo", nullable = false)
    private String titulo;
    @Column(name = "descricao", nullable = false)
    private String descricao;
    @Column(name = "link", nullable = false)
    private String link;
    @Column(name = "processada", nullable = false, columnDefinition = "default = 'N'")
    private String processada;
}
