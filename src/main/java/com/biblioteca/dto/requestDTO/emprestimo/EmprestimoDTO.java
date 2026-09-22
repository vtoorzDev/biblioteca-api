package com.biblioteca.dto.requestDTO.emprestimo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmprestimoDTO {
    @NotNull(message = "O campo de usuario deve ser preenchido")
    private Long usuarioId;

    @NotNull(message = "O campo de livro deve ser preenchido")
    private Long livroId;
}
