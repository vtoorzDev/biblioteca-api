package com.biblioteca.entity.emprestimo;

import com.biblioteca.entity.livro.LivroEntity;
import com.biblioteca.entity.usuario.UsuarioEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Emprestimos")
@Data
public class EmprestimoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuarioEntity;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private LivroEntity livroEntity;

    private LocalDate dataEmprestimo;
    private boolean status;
}
