package com.biblioteca.service.emprestimo;

import com.biblioteca.dto.requestDTO.emprestimo.EmprestimoRequestDTO;
import com.biblioteca.dto.responseDTO.emprestimo.EmprestimoResponseDTO;
import com.biblioteca.entity.emprestimo.EmprestimoEntity;
import com.biblioteca.entity.livro.LivroEntity;
import com.biblioteca.entity.usuario.UsuarioEntity;
import com.biblioteca.exception.emprestimo.EmprestimoException;
import com.biblioteca.exception.livro.LivroException;
import com.biblioteca.exception.usuario.UsuarioException;
import com.biblioteca.repository.emprestimo.EmprestimoRepository;
import com.biblioteca.repository.livro.LivroRepository;
import com.biblioteca.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    private EmprestimoResponseDTO transformarResponse(EmprestimoEntity emprestimoEntity) {
        EmprestimoResponseDTO emprestimoResponseDTO = new EmprestimoResponseDTO();

        emprestimoResponseDTO.setId(emprestimoEntity.getId());
        emprestimoResponseDTO.setDataEmprestimo(emprestimoEntity.getDataEmprestimo());
        emprestimoResponseDTO.setLivroId(emprestimoEntity.getLivroEntity().getId());
        emprestimoResponseDTO.setUsuarioId(emprestimoEntity.getUsuarioEntity().getId());
        emprestimoResponseDTO.setStatus(emprestimoEntity.isStatus());

        return emprestimoResponseDTO;

    }

    public List<EmprestimoResponseDTO> listarEmprestimos(){
        return emprestimoRepository.findAll().stream().map(this::transformarResponse).toList();
    }

    public EmprestimoResponseDTO cadastrarEmprestimo(EmprestimoRequestDTO emprestimoRequestDTO, Long idEmprestimo, Long usuarioId, Long livroId) {
        Optional<EmprestimoEntity> emprestimoEncontrado = emprestimoRepository.findById(idEmprestimo);
        Optional<UsuarioEntity> usuarioEncontrado = usuarioRepository.findById(usuarioId);
        Optional<LivroEntity> livroEncontrado = livroRepository.findById(livroId);

        if (emprestimoEncontrado.isPresent()) {
            throw new EmprestimoException("esse emprestimo ja foi feito");
        }

        if (usuarioEncontrado.isEmpty()) {
            throw new UsuarioException("Não encontramos esse usuario no sistema");
        }

        if (livroEncontrado.isEmpty()) {
            throw new LivroException("Não encontramos esse livro no sistema");
        }

        EmprestimoEntity emprestimoCadastrar = new EmprestimoEntity();
            emprestimoCadastrar.setStatus(true);
            emprestimoCadastrar.setUsuarioEntity(usuarioEncontrado.get());
            emprestimoCadastrar.setLivroEntity(livroEncontrado.get());

            emprestimoRepository.save(emprestimoCadastrar);

            return transformarResponse(emprestimoCadastrar);
        }

        public void deletarEmprestimo(Long id) {
            Optional<EmprestimoEntity> emprestimoEncontrado = emprestimoRepository.findById(id);

            if (emprestimoEncontrado.isEmpty()) {
                throw new EmprestimoException("Emprestimo não encontrado no sistema");
            }
            emprestimoRepository.delete(emprestimoEncontrado.get());
        }
    }

