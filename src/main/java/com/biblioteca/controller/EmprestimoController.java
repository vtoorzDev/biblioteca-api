package com.biblioteca.controller;

import com.biblioteca.dto.requestDTO.emprestimo.EmprestimoDTO;
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
    public EmprestimoEntity cadastrarEmprestimo(@Valid @RequestBody EmprestimoDTO emprestimoDTO){
        return emprestimoService.cadastrarEmprestimo(emprestimoDTO);
    }
    @DeleteMapping("/deletar/{id}")
    public void deletarEmprestimo(@PathVariable Long id){
        emprestimoService.deletarEmprestimo(id);
    }

    @GetMapping("/listar")
    public List<EmprestimoEntity> listarEmprestimos(){
        return emprestimoService.listarEmprestimos();
    }
    @GetMapping("/buscar/{id}")
    public EmprestimoEntity buscarEmprestimoPorId(@PathVariable Long id){
        return emprestimoService.buscarEmprestimoPorId(id);
    }

}
