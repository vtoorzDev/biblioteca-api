package com.biblioteca.repository;

import com.biblioteca.model.LivroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository <LivroModel, Long>{

    boolean existsByIsbn(String isbn);
    List<LivroModel> findByTituloContainingIgnoreCase(String titulo);
    List<LivroModel> findByAutorContainingIgnoreCase(String autor);
}
