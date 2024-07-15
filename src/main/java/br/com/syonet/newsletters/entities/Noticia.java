package br.com.syonet.newsletters.entities;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_noticia")
    @SequenceGenerator(name = "s_noticia", sequenceName = "s_noticia", allocationSize = 1)
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
