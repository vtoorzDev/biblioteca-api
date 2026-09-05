package com.biblioteca.controller;


import com.biblioteca.dto.LivroRequestDTO;
import com.biblioteca.model.LivroModel;
import com.biblioteca.service.LivroService;
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

    @GetMapping
    @Operation(
            summary = "Listar todos os livros",
            description = "Retorna uma lista contendo todos os livros cadastrados na biblioteca"
    )
    public List<LivroModel> listarLivros(){
        return livroService.listarLivros();
    }

    @Operation(
            summary = "Buscar o livro pelo id",
            description = "Retorna o livro pelo id pesquisado e retorna uma exceção personalizada caso o id não exista no banco de dados"
    )
    @GetMapping("/{id}")
    public LivroModel buscarLivroPorId(@PathVariable Long id){
        return livroService.buscarLivroPorId(id);
    }

    @Operation(
            summary = "Buscar livros pelo nome do autor",
            description = "Retorna o livro pelo autor pesquisado e retorna uma exceção personalizada caso o autor não exista no banco de dados"
    )
    @GetMapping("/buscar/autor")
    public List<LivroModel> buscarPorAutor(@RequestParam String autor){
        return livroService.buscarPorAutor(autor);
    }

    @Operation(
            summary = "Buscar livros pelo titulo do livro",
            description = "Retorna o livro pelo titulo pesquisado e retorna uma exceção personalizada caso o titulo não exista no banco de dados"
    )
    @GetMapping("/buscar/titulo")
    public List<LivroModel> buscarPorTitulo(@RequestParam String titulo) {
        return livroService.buscarPorTitulo(titulo);
    }

    @Operation(
            summary = "Cadastrar livro",
            description = "Cadastra o livro e valida caso algum campo nao for preenchido"
    )
    @PostMapping
    public LivroModel cadastrarLivro(@Valid @RequestBody LivroRequestDTO livroRequestDTO){
        LivroModel livroModel = new LivroModel();

        livroModel.setTitulo(livroRequestDTO.getTitulo());
        livroModel.setAutor(livroRequestDTO.getAutor());
        livroModel.setIsbn(livroRequestDTO.getIsbn());
        livroModel.setAnoPublicacao(livroRequestDTO.getAnoPublicacao());
        livroModel.setCategoria(livroRequestDTO.getCategoria());
        livroModel.setQuantidade(livroRequestDTO.getQuantidade());

        return livroService.cadastrarLivro(livroModel);
    }


    @Operation(
            summary = "Atualizar Livro",
            description = "Atualiza o livro pesquisado pelo id, caso o id não exista no banco de dados retorna uma exção personalizada e valida os campos."
    )
    @PutMapping("/{id}")
    public LivroModel atualizarLivro(@Valid @RequestBody LivroRequestDTO livroRequestDTO, @PathVariable Long id){

        LivroModel livroModel = new LivroModel();

        livroModel.setTitulo(livroRequestDTO.getTitulo());
        livroModel.setAutor(livroRequestDTO.getAutor());
        livroModel.setIsbn(livroRequestDTO.getIsbn());
        livroModel.setAnoPublicacao(livroRequestDTO.getAnoPublicacao());
        livroModel.setCategoria(livroRequestDTO.getCategoria());
        livroModel.setQuantidade(livroRequestDTO.getQuantidade());

        return livroService.atualizarLivro(id, livroModel);
    }

    @Operation(
            summary = "Deletar livro",
            description = "Deleta livro pelo id e retorna uma exceção personalizada caso o id não exista no banco de dados"
    )
    @DeleteMapping("/{id}")
    public void deletarLivroPorId(@PathVariable Long id){
        livroService.deletarLivro(id);
    }
}
