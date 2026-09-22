package com.biblioteca.entity.livro;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "livros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;
    private String categoria;
    private Integer quantidade;
}
