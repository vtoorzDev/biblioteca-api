package com.biblioteca.service.emprestimo;

import com.biblioteca.dto.requestDTO.emprestimo.EmprestimoDTO;
import com.biblioteca.entity.emprestimo.EmprestimoEntity;
import com.biblioteca.entity.livro.LivroEntity;
import com.biblioteca.entity.usuario.UsuarioEntity;
import com.biblioteca.repository.emprestimo.EmprestimoRepository;
import com.biblioteca.repository.livro.LivroRepository;
import com.biblioteca.repository.usuario.UsuarioRepository;
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

    public List<EmprestimoEntity> listarEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public EmprestimoEntity cadastrarEmprestimo(EmprestimoDTO emprestimoDTO) {

        UsuarioEntity usuario = usuarioRepository
                .findById(emprestimoDTO.getUsuarioId())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        LivroEntity livro = livroRepository
                .findById(emprestimoDTO.getLivroId())
                .orElseThrow(LivroNaoEncontradoException::new);

        boolean livroEmprestado =
                emprestimoRepository.existsByLivroModelAndStatus(livro, true);

        if (livroEmprestado) {
            throw new LivroNaoDisponivelException();
        }

        EmprestimoEntity emprestimo = new EmprestimoEntity();

        emprestimo.setUsuarioEntity(usuario);
        emprestimo.setLivroEntity(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setStatus(true);

        return emprestimoRepository.save(emprestimo);
    }

    public EmprestimoEntity buscarEmprestimoPorId(Long id) {
        return emprestimoRepository.findById(id).orElse(null);
    }

    public void deletarEmprestimo(Long id) {

        EmprestimoEntity emprestimoEncontrado =
                emprestimoRepository.findById(id).orElse(null);

        if (emprestimoEncontrado != null) {
            emprestimoRepository.deleteById(id);
        }
    }
}