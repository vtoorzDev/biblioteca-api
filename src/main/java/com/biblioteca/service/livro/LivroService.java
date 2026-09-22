package com.biblioteca.service.livro;

import com.biblioteca.entity.livro.LivroEntity;
import com.biblioteca.repository.livro.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<LivroEntity> listarLivros() {
        return livroRepository.findAll();
    }

    public LivroEntity buscarLivroPorId(Long id) {
        LivroEntity livro = livroRepository.findById(id).orElse(null);

        if (livro != null) {
            return livro;
        }

        throw new LivroNaoEncontradoException();
    }

    public LivroEntity cadastrarLivro(LivroEntity livroEntity) {
        if (!livroRepository.existsByIsbn(livroEntity.getIsbn())) {
            return livroRepository.save(livroEntity);
        }

        throw new IsbnDuplicadoException();
    }

    public LivroEntity atualizarLivro(Long id, LivroEntity livroEntity) {
        LivroEntity livro = livroRepository.findById(id).orElse(null);

        if (livro == null) {
            throw new LivroNaoEncontradoException();
        }

        if (livroRepository.existsByIsbn(livroEntity.getIsbn())
                && !livro.getIsbn().equals(livroEntity.getIsbn())) {
            throw new IsbnDuplicadoException();
        }

        livro.setAutor(livroEntity.getAutor());
        livro.setTitulo(livroEntity.getTitulo());
        livro.setIsbn(livroEntity.getIsbn());
        livro.setAnoPublicacao(livroEntity.getAnoPublicacao());
        livro.setCategoria(livroEntity.getCategoria());
        livro.setQuantidade(livroEntity.getQuantidade());

        return livroRepository.save(livro);
    }

    public List<LivroEntity> buscarPorTitulo(String titulo){
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<LivroEntity> buscarPorAutor(String autor){
        return livroRepository.findByAutorContainingIgnoreCase(autor);
    }

    public void deletarLivro(Long id){
        LivroEntity livro = livroRepository.findById(id).orElse(null);

        if (livro != null){
            livroRepository.deleteById(id);
            return;
        }

        throw new LivroNaoEncontradoException();
    }
}