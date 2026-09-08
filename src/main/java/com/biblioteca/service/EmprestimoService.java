package com.biblioteca.service;

import com.biblioteca.dto.EmprestimoDTO;
import com.biblioteca.exception.EmprestimoNaoEncontradoException;
import com.biblioteca.exception.LivroNaoDisponivelException;
import com.biblioteca.exception.LivroNaoEncontradoException;
import com.biblioteca.exception.UsuarioNaoEncontradoException;
import com.biblioteca.model.EmprestimoModel;
import com.biblioteca.model.LivroModel;
import com.biblioteca.model.UsuarioModel;
import com.biblioteca.repository.EmprestimoRepository;
import com.biblioteca.repository.LivroRepository;
import com.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;

    public EmprestimoService(
            EmprestimoRepository emprestimoRepository,
            UsuarioRepository usuarioRepository,
            LivroRepository livroRepository) {

        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
    }

    public List<EmprestimoModel> listarEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public EmprestimoModel cadastrarEmprestimo(EmprestimoDTO emprestimoDTO) {

        UsuarioModel usuario = usuarioRepository
                .findById(emprestimoDTO.getUsuarioId())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        LivroModel livro = livroRepository
                .findById(emprestimoDTO.getLivroId())
                .orElseThrow(LivroNaoEncontradoException::new);

        boolean livroEmprestado =
                emprestimoRepository.existsByLivroModelAndStatus(livro, true);

        if (livroEmprestado) {
            throw new LivroNaoDisponivelException();
        }

        EmprestimoModel emprestimo = new EmprestimoModel();

        emprestimo.setUsuarioModel(usuario);
        emprestimo.setLivroModel(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setStatus(true);

        return emprestimoRepository.save(emprestimo);
    }

    public EmprestimoModel buscarEmprestimoPorId(Long id) {
        return emprestimoRepository.findById(id).orElse(null);
    }

    public void deletarEmprestimo(Long id) {

        EmprestimoModel emprestimoEncontrado =
                emprestimoRepository.findById(id).orElse(null);

        if (emprestimoEncontrado != null) {
            emprestimoRepository.deleteById(id);
        }
    }
}