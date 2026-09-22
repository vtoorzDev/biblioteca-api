package com.biblioteca.controller;

import com.biblioteca.dto.requestDTO.usuario.UsuarioRequestDTO;
import com.biblioteca.entity.usuario.UsuarioEntity;
import com.biblioteca.service.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(
        name = "usuarios",
        description = "Operações para gerenciar usuarios"
)
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastrar")
    @Operation(
            summary = "Cadastrar Usuario",
            description = "O usuário digita os dados respectivos para cadastrar, caso deixe algo em branco irá realizar a mensagem de validação"
    )
    public UsuarioEntity cadastrarUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO){
        return usuarioService.cadastrarUsuario(usuarioRequestDTO);
    }
    @PutMapping("/atualizar/{id}")
    @Operation(
            summary = "Atualizar Usuário",
            description = "O usuário digita os dados respectivos para atualizar, caso deixe algo em branco irá realizar a mensagem de validação"
            )
    public UsuarioEntity atualizarUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO, @PathVariable Long id){
        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setNome(usuarioRequestDTO.getNome());
        usuarioEntity.setTelefone(usuarioRequestDTO.getTelefone());
        usuarioEntity.setEmail(usuarioRequestDTO.getEmail());
        usuarioEntity.setDataNascimento(usuarioRequestDTO.getData_nascimento());

        return usuarioService.atualizarUsuario(id, usuarioEntity);

    }

    @DeleteMapping("/deletar/{id}")
    @Operation(
            summary = "Deletar Usuario",
            description = "O usuário vai digitar o id do usuário que queira deletar do banco de dados, caso o id não esteja no banco de dados irá realizar a mensagem personalizada"
    )
    public void deletarUsuario(@Valid @PathVariable long id){
        usuarioService.deletarUsuario(id);
    }


    @GetMapping("/buscar/nome")
    @Operation(
            summary = "Buscar Usuario PeloNome",
            description = "O usuário digita o nome do usuário que queira encontrar, caso o nome não esteja no banco de dados irá realizar a mensagem personalizada"
    )
    public List<UsuarioEntity> buscarUsuarioPeloNome(@RequestParam String nome){
        return usuarioService.buscarUsuarioPeloNome(nome);
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar Usuarios Cadastrados",
            description = "O usuario assim que solicitar o get de listar usuário vai aparecer a lista de todos os usuários cadastrados no banco de dados"
    )
    public List<UsuarioEntity> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/buscar/{id}")
    @Operation(
            summary = "Buscar Usuário pelo Id",
            description = "O usuário vai digitar o numero do id que ele quer encontrar e vai aparecer o id correspondente, caso não esteja cadastrado vai aparecer a mersagem personalizada"
    )
    public UsuarioEntity buscarUsuarioPorId(@PathVariable Long id){
        return usuarioService.buscarUsuarioPorId(id);
    }
}
