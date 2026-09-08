package com.biblioteca.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class EmprestimoDTO {
    @NotNull(message = "O campo de usuario deve ser preenchido")
    private Long usuarioId;

    @NotNull(message = "O campo de livro deve ser preenchido")
    private Long livroId;
}
