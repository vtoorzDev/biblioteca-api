package com.biblioteca.service;

import com.biblioteca.dto.UsuarioRequestDTO;
import com.biblioteca.exception.UsuarioNaoEncontradoException;
import com.biblioteca.model.UsuarioModel;
import com.biblioteca.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class UsuarioService {
        private final UsuarioRepository usuarioRepository;

        public UsuarioService(UsuarioRepository usuarioRepository){
            this.usuarioRepository = usuarioRepository;

        }

        private UsuarioModel encontrarIdUsuario(Long id){
            return usuarioRepository.findById(id).orElse(null);
        }

        public UsuarioModel cadastrarUsuario(@Valid UsuarioRequestDTO usuarioRequestDTO){
                UsuarioModel usuario = new UsuarioModel();

                usuario.setNome(usuarioRequestDTO.getNome());
                usuario.setEmail(usuarioRequestDTO.getEmail());
                usuario.setTelefone(usuarioRequestDTO.getTelefone());
                usuario.setDataNascimento(usuarioRequestDTO.getData_nascimento());

                return usuarioRepository.save(usuario);
        }

        public List<UsuarioModel> listarUsuarios(){
            return usuarioRepository.findAll();
        }

        public UsuarioModel buscarUsuarioPorId(Long id){
           UsuarioModel usuarioEncontrado = encontrarIdUsuario(id);

           if (usuarioEncontrado != null){
                return usuarioEncontrado;
           }
           throw new UsuarioNaoEncontradoException();
        }

        public List<UsuarioModel> buscarUsuarioPeloNome(String nome) {
            return usuarioRepository.findByNomeContainingIgnoreCase(nome);
        }

        public UsuarioModel atualizarUsuario(Long id, UsuarioModel usuarioModel){
            UsuarioModel usuarioEncontrado = encontrarIdUsuario(id);

            if (usuarioEncontrado != null){
                usuarioEncontrado.setNome(usuarioModel.getNome());
                usuarioEncontrado.setEmail(usuarioModel.getEmail());
                usuarioEncontrado.setTelefone(usuarioModel.getTelefone());
                usuarioEncontrado.setDataNascimento(usuarioModel.getDataNascimento());

                return usuarioRepository.save(usuarioEncontrado);
            }
            throw new UsuarioNaoEncontradoException();
            }

        public void deletarUsuario(Long id){
            UsuarioModel usuarioEncontrado = encontrarIdUsuario(id);

            if (usuarioEncontrado != null){
                usuarioRepository.delete(usuarioEncontrado);
            } else {
                throw new UsuarioNaoEncontradoException();
            }
        }
    }
