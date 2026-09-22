package com.biblioteca.dto.responseDTO.emprestimo;

import com.biblioteca.entity.livro.LivroEntity;
import com.biblioteca.entity.usuario.UsuarioEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmprestimoResponseDTO {
    private Long id;
    private UsuarioEntity usuarioEntity;
    private LivroEntity livroEntity;
    private LocalDate dataEmprestimo;
    private boolean status;
}
