package com.biblioteca.service.livro;

import com.biblioteca.dto.requestDTO.livro.LivroRequestDTO;
import com.biblioteca.dto.responseDTO.livro.LivroResponseDTO;
import com.biblioteca.entity.livro.LivroEntity;
import com.biblioteca.exception.livro.LivroException;
import com.biblioteca.repository.livro.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<LivroResponseDTO> listarLivros() {
        return livroRepository.findAll().stream().map(this::transformarResponse).toList();
    }

    private LivroResponseDTO transformarResponse(LivroEntity livroEntity) {
        LivroResponseDTO livroResponseDTO = new LivroResponseDTO();

        livroResponseDTO.setId(livroEntity.getId());
        livroResponseDTO.setIsbn(livroEntity.getIsbn());
        livroResponseDTO.setTitulo(livroEntity.getTitulo());
        livroResponseDTO.setCategoria(livroEntity.getCategoria());
        livroResponseDTO.setAutor(livroEntity.getAutor());
        livroResponseDTO.setQuantidade(livroEntity.getQuantidade());
        livroResponseDTO.setAnoPublicacao(livroEntity.getAnoPublicacao());

        return livroResponseDTO;
    }

    public LivroResponseDTO buscarLivroPorId(Long id) {
        Optional<LivroEntity> livroEncontrado = livroRepository.findById(id);

        if(livroEncontrado.isEmpty()) {
            throw new LivroException("Livro não cadastrado no nosso sistema");
        }
        return transformarResponse(livroEncontrado.get());
    }

    public LivroResponseDTO cadastrarLivro(LivroRequestDTO livroRequestDTO, Long id) {
        Optional<LivroEntity> livroEncontrado = livroRepository.findById(id);

        if (livroEncontrado.isEmpty()) {
            LivroEntity livroCadastrado = new LivroEntity();

            livroCadastrado.setIsbn(livroRequestDTO.getIsbn());
            livroCadastrado.setAutor(livroRequestDTO.getAutor());
            livroCadastrado.setTitulo(livroRequestDTO.getTitulo());
            livroCadastrado.setCategoria(livroRequestDTO.getCategoria());
            livroCadastrado.setQuantidade(livroRequestDTO.getQuantidade());
            livroCadastrado.setAnoPublicacao(livroRequestDTO.getAnoPublicacao());

            livroRepository.save(livroCadastrado);

            return transformarResponse(livroCadastrado);
        }

        throw new LivroException("Livrvo já cadastrado no sistema");
    }

    public LivroResponseDTO atualizarLivro(LivroRequestDTO livroRequestDTO, Long id) {
        Optional<LivroEntity> livroEncontrado = livroRepository.findById(id);

        if (livroEncontrado.isPresent()) {
            LivroEntity livroAtualizado = livroEncontrado.get();

            livroAtualizado.setIsbn(livroRequestDTO.getIsbn());
            livroAtualizado.setAutor(livroRequestDTO.getAutor());
            livroAtualizado.setTitulo(livroRequestDTO.getTitulo());
            livroAtualizado.setCategoria(livroRequestDTO.getCategoria());
            livroAtualizado.setQuantidade(livroRequestDTO.getQuantidade());
            livroAtualizado.setAnoPublicacao(livroRequestDTO.getAnoPublicacao());

            livroRepository.save(livroAtualizado);

            return transformarResponse(livroAtualizado);
        }

        throw new LivroException("Livro não encontrado no sistema");
    }
    public List<LivroResponseDTO> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo).stream().map(this::transformarResponse).toList();
    }

    public List<LivroResponseDTO> buscarPorAutor(String autor){
        return livroRepository.findByAutorContainingIgnoreCase(autor).stream().map(this::transformarResponse).toList();
    }

    public void deletarLivro(Long id){
        Optional<LivroEntity> livroEncontrado = livroRepository.findById(id);

        if (livroEncontrado.isPresent()) {
            livroRepository.delete(livroEncontrado.get());
        } else {
            throw new LivroException("Livro não encontrado ");
        }
    }
}