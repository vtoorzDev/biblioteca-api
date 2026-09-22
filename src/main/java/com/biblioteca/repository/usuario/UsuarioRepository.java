package com.biblioteca.repository.usuario;

import com.biblioteca.entity.usuario.UsuarioEntity;
import jdk.dynalink.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    List<UsuarioEntity> findByNome(String nome);
    Optional<UsuarioEntity> findById(Long id);
}