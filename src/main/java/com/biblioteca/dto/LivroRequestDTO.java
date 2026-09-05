package com.biblioteca.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroRequestDTO {
    @NotBlank(message = "O titulo não pode ser nulo")
    @Size(min = 5, max = 250, message = "O titulo deve conter entre 5 e 250 caracteres")
    private String titulo;

    @NotBlank(message = "O autor não pode ser nulo")
    @Size(min = 3, max = 100, message = "O nome do autor deve ter entre 3 e 100 caracteres")
    private String autor;

    @NotBlank(message = "O isbn não pode ser nulo")
    @Size(min = 3, max = 100, message = "O isbn deve ter entre 3 e 100 caracteres")
    private String isbn;

    @NotNull(message = "O ano de publicação é obrigatório")
    private Integer anoPublicacao;

    @NotBlank(message = "A categoria não pode ser nula")
    @Size(min = 3, max = 100, message = "A categoria deve ter entre 3 e 100 caracteres")
    private String categoria;

    @NotNull(message = "você deve digitar a quantidade de livros")
    @Min(value = 0, message = "A quantidade não pode ser negativa")
    private Integer quantidade;
}
