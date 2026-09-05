package com.biblioteca.service;

import com.biblioteca.exception.IsbnDuplicadoException;
import com.biblioteca.exception.LivroNaoEncontradoException;
import com.biblioteca.model.LivroModel;
import com.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<LivroModel> listarLivros() {
        return livroRepository.findAll();
    }

    public LivroModel buscarLivroPorId(Long id) {
        LivroModel livro = livroRepository.findById(id).orElse(null);

        if (livro != null) {
            return livro;
        }

        throw new LivroNaoEncontradoException();
    }

    public LivroModel cadastrarLivro(LivroModel livroModel) {
        if (!livroRepository.existsByIsbn(livroModel.getIsbn())) {
            return livroRepository.save(livroModel);
        }

        throw new IsbnDuplicadoException();
    }

    public LivroModel atualizarLivro(Long id, LivroModel livroModel) {
        LivroModel livro = livroRepository.findById(id).orElse(null);

        if (livro == null) {
            throw new LivroNaoEncontradoException();
        }

        if (livroRepository.existsByIsbn(livroModel.getIsbn())
                && !livro.getIsbn().equals(livroModel.getIsbn())) {
            throw new IsbnDuplicadoException();
        }

        livro.setAutor(livroModel.getAutor());
        livro.setTitulo(livroModel.getTitulo());
        livro.setIsbn(livroModel.getIsbn());
        livro.setAnoPublicacao(livroModel.getAnoPublicacao());
        livro.setCategoria(livroModel.getCategoria());
        livro.setQuantidade(livroModel.getQuantidade());

        return livroRepository.save(livro);
    }

    public List<LivroModel> buscarPorTitulo(String titulo){
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<LivroModel> buscarPorAutor(String autor){
        return livroRepository.findByAutorContainingIgnoreCase(autor);
    }

    public void deletarLivro(Long id){
        LivroModel livro = livroRepository.findById(id).orElse(null);

        if (livro != null){
            livroRepository.deleteById(id);
            return;
        }

        throw new LivroNaoEncontradoException();
    }
}