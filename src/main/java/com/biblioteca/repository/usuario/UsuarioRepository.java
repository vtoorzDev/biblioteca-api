package com.biblioteca.repository.usuario;

import com.biblioteca.entity.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    List<UsuarioEntity> findByNomeContainingIgnoreCase(String nome);
}