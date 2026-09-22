package com.biblioteca.service.usuario;

import com.biblioteca.dto.requestDTO.usuario.UsuarioRequestDTO;
import com.biblioteca.dto.responseDTO.usuario.UsuarioResponseDTO;
import com.biblioteca.entity.usuario.UsuarioEntity;
import com.biblioteca.exception.usuario.UsuarioException;
import com.biblioteca.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;

    }

    private UsuarioResponseDTO transformarEmResponse(UsuarioEntity usuarioEntity) {
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setId(usuarioEntity.getId());
        usuarioResponseDTO.setNome(usuarioEntity.getNome());
        usuarioResponseDTO.setDataNascimento(usuarioEntity.getDataNascimento());
        usuarioResponseDTO.setTelefone(usuarioEntity.getTelefone());
        usuarioResponseDTO.setEmail(usuarioEntity.getEmail());

        return usuarioResponseDTO;
    }

    public UsuarioResponseDTO cadastrarUsuario (UsuarioRequestDTO usuarioRequestDTO, Long id) {

        Optional<UsuarioEntity> usuarioEncontrado = usuarioRepository.findById(id);

        if (usuarioEncontrado.isEmpty()) {
            UsuarioEntity usuarioCadastrado = new UsuarioEntity();

            usuarioCadastrado.setNome(usuarioRequestDTO.getNome());
            usuarioCadastrado.setDataNascimento(usuarioRequestDTO.getDataNascimento());
            usuarioCadastrado.setTelefone(usuarioRequestDTO.getTelefone());
            usuarioCadastrado.setEmail(usuarioRequestDTO.getEmail());

            usuarioRepository.save(usuarioCadastrado);

            return transformarEmResponse(usuarioCadastrado);
        }
        throw new UsuarioException("Usuário ja cadastrado no sistema");
    }

    public List<UsuarioResponseDTO> listarUsuarios(){
        return usuarioRepository.findAll().stream().map(this::transformarEmResponse).toList();
    }

    public UsuarioResponseDTO buscarUsuarioPorId(Long id){
        Optional<UsuarioEntity> usuarioEncontrado = usuarioRepository.findById(id);

        if (usuarioEncontrado.isEmpty()) {
            throw new UsuarioException("Usuario não encontrado no sistema");
        }
        return transformarEmResponse(usuarioEncontrado.get());
    }

    public List<UsuarioResponseDTO> buscarUsuarioPorNome(String nome) {
        return usuarioRepository.findByNome(nome).stream().map(this::transformarEmResponse).toList();
    }

    public UsuarioResponseDTO atualizarUsuario(UsuarioRequestDTO usuarioRequestDTO, Long id) {
        Optional<UsuarioEntity> usuarioEncontrado = usuarioRepository.findById(id);

        if (usuarioEncontrado.isPresent()) {
            UsuarioEntity usuarioAtualizado = usuarioEncontrado.get();

            usuarioAtualizado.setNome(usuarioRequestDTO.getNome());
            usuarioAtualizado.setDataNascimento(usuarioRequestDTO.getDataNascimento());
            usuarioAtualizado.setTelefone(usuarioRequestDTO.getTelefone());
            usuarioAtualizado.setEmail(usuarioRequestDTO.getEmail());

            usuarioRepository.save(usuarioAtualizado);

            return transformarEmResponse(usuarioAtualizado);
        }

        throw new UsuarioException("Usuário não encontrado no sistema");
    }

    public void deletarUsuario(Long id) {
        Optional<UsuarioEntity> usuarioEncontrado = usuarioRepository.findById(id);

        if (usuarioEncontrado.isEmpty()) {
            throw new UsuarioException("Usuário não encontrado no sistema");
        }
        usuarioRepository.delete(usuarioEncontrado.get());
    }
}