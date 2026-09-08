package com.biblioteca.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Emprestimos")
@Data
public class EmprestimoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuarioModel;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private LivroModel livroModel;

    private LocalDate dataEmprestimo;
    private boolean status;
}
