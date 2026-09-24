package com.biblioteca.controller.emprestimo;

import com.biblioteca.dto.requestDTO.emprestimo.EmprestimoRequestDTO;
import com.biblioteca.dto.responseDTO.emprestimo.EmprestimoResponseDTO;
import com.biblioteca.entity.emprestimo.EmprestimoEntity;
import com.biblioteca.service.emprestimo.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService){
        this.emprestimoService = emprestimoService;
    }

    @PostMapping("/cadastrar")
    public EmprestimoResponseDTO cadastrarEmprestimo(@Valid @RequestBody EmprestimoRequestDTO emprestimoRequestDTO, @RequestParam Long idEmprestimo, @RequestParam Long idUsuario, @RequestParam Long idLivro ){
        return emprestimoService.cadastrarEmprestimo(emprestimoRequestDTO, idEmprestimo, idUsuario, idLivro);
    }
    @DeleteMapping("/deletar/{id}")
    public void deletarEmprestimo(@PathVariable Long id){
        emprestimoService.deletarEmprestimo(id);
    }

    @GetMapping("/listar")
    public List<EmprestimoResponseDTO> listarEmprestimos(){
        return emprestimoService.listarEmprestimos();
    }


}
