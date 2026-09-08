package com.biblioteca.repository;

import com.biblioteca.model.EmprestimoModel;
import com.biblioteca.model.LivroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository <EmprestimoModel, Long>{
    boolean existsByLivroModelAndStatus(LivroModel livroModel, boolean status   );
}
