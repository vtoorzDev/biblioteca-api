package com.biblioteca.repository.emprestimo;

import com.biblioteca.entity.emprestimo.EmprestimoEntity;
import com.biblioteca.entity.livro.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository <EmprestimoEntity, Long>{
    boolean existsByLivroModelAndStatus(LivroEntity livroEntity, boolean status   );
    List<EmprestimoEntity> findByUsuarioId(Long UsuarioId);
}
