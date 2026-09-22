package com.biblioteca.repository.livro;

import com.biblioteca.entity.livro.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository <LivroEntity, Long>{

    boolean existsByIsbn(String isbn);
    List<LivroEntity> findByTituloContainingIgnoreCase(String titulo);
    List<LivroEntity> findByAutorContainingIgnoreCase(String autor);
}
