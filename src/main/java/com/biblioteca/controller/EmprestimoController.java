package com.biblioteca.controller;

import com.biblioteca.dto.EmprestimoDTO;
import com.biblioteca.model.EmprestimoModel;
import com.biblioteca.service.EmprestimoService;
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
    public EmprestimoModel cadastrarEmprestimo(@Valid @RequestBody EmprestimoDTO emprestimoDTO){
        return emprestimoService.cadastrarEmprestimo(emprestimoDTO);
    }
    @DeleteMapping("/deletar/{id}")
    public void deletarEmprestimo(@PathVariable Long id){
        emprestimoService.deletarEmprestimo(id);
    }

    @GetMapping("/listar")
    public List<EmprestimoModel> listarEmprestimos(){
        return emprestimoService.listarEmprestimos();
    }
    @GetMapping("/buscar/{id}")
    public EmprestimoModel buscarEmprestimoPorId(@PathVariable Long id){
        return emprestimoService.buscarEmprestimoPorId(id);
    }

}
