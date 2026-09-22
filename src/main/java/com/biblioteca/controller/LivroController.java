package com.biblioteca.controller;


import com.biblioteca.dto.requestDTO.livro.LivroRequestDTO;
import com.biblioteca.entity.livro.LivroEntity;
import com.biblioteca.service.livro.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@Tag(
        name = "Livros",
        description = "Operações para gerenciamento de livros"
)
public class LivroController {
    private final  LivroService livroService;

    public LivroController(LivroService livroService){
        this.livroService = livroService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar todos os livros",
            description = "Retorna uma lista contendo todos os livros cadastrados na biblioteca"
    )
    public List<LivroEntity> listarLivros(){
        return livroService.listarLivros();
    }

    @Operation(
            summary = "Buscar o livro pelo id",
            description = "Retorna o livro pelo id pesquisado e retorna uma exceção personalizada caso o id não exista no banco de dados"
    )
    @GetMapping("/buscar/{id}")
    public LivroEntity buscarLivroPorId(@PathVariable Long id){
        return livroService.buscarLivroPorId(id);
    }

    @Operation(
            summary = "Buscar livros pelo nome do autor",
            description = "Retorna o livro pelo autor pesquisado e retorna uma exceção personalizada caso o autor não exista no banco de dados"
    )
    @GetMapping("/buscar/autor/{autor}")
    public List<LivroEntity> buscarPorAutor(@PathVariable String autor){
        return livroService.buscarPorAutor(autor);
    }

    @Operation(
            summary = "Buscar livros pelo titulo do livro",
            description = "Retorna o livro pelo titulo pesquisado e retorna uma exceção personalizada caso o titulo não exista no banco de dados"
    )
    @GetMapping("/buscar/titulo/{titulo}")
    public List<LivroEntity> buscarPorTitulo(@PathVariable String titulo) {
        return livroService.buscarPorTitulo(titulo);
    }

    @Operation(
            summary = "Cadastrar livro",
            description = "Cadastra o livro e valida caso algum campo nao for preenchido"
    )
    @PostMapping("/cadastrar")
    public LivroEntity cadastrarLivro(@Valid @RequestBody LivroRequestDTO livroRequestDTO){
        LivroEntity livroEntity = new LivroEntity();

        livroEntity.setTitulo(livroRequestDTO.getTitulo());
        livroEntity.setAutor(livroRequestDTO.getAutor());
        livroEntity.setIsbn(livroRequestDTO.getIsbn());
        livroEntity.setAnoPublicacao(livroRequestDTO.getAnoPublicacao());
        livroEntity.setCategoria(livroRequestDTO.getCategoria());
        livroEntity.setQuantidade(livroRequestDTO.getQuantidade());

        return livroService.cadastrarLivro(livroEntity);
    }


    @Operation(
            summary = "Atualizar Livro",
            description = "Atualiza o livro pesquisado pelo id, caso o id não exista no banco de dados retorna uma exção personalizada e valida os campos."
    )
    @PutMapping("/atualizar/{id}")
    public LivroEntity atualizarLivro(@Valid @RequestBody LivroRequestDTO livroRequestDTO, @PathVariable Long id){

        LivroEntity livroEntity = new LivroEntity();

        livroEntity.setTitulo(livroRequestDTO.getTitulo());
        livroEntity.setAutor(livroRequestDTO.getAutor());
        livroEntity.setIsbn(livroRequestDTO.getIsbn());
        livroEntity.setAnoPublicacao(livroRequestDTO.getAnoPublicacao());
        livroEntity.setCategoria(livroRequestDTO.getCategoria());
        livroEntity.setQuantidade(livroRequestDTO.getQuantidade());

        return livroService.atualizarLivro(id, livroEntity);
    }

    @Operation(
            summary = "Deletar livro",
            description = "Deleta livro pelo id e retorna uma exceção personalizada caso o id não exista no banco de dados"
    )
    @DeleteMapping("/deletar/{id}")
    public void deletarLivroPorId(@PathVariable Long id){
        livroService.deletarLivro(id);
    }
}
