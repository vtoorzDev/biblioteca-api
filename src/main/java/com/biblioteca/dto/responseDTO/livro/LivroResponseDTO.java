package com.biblioteca.dto.responseDTO.livro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroResponseDTO {
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private Integer anoPublicacao;
    private String categoria;
    private Integer quantidade;
}
