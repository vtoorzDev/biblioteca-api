package com.biblioteca.service.usuario;

import com.biblioteca.dto.requestDTO.usuario.UsuarioRequestDTO;
import com.biblioteca.entity.usuario.UsuarioEntity;
import com.biblioteca.repository.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class UsuarioService {
        private final UsuarioRepository usuarioRepository;

        public UsuarioService(UsuarioRepository usuarioRepository){
            this.usuarioRepository = usuarioRepository;

        }

        private UsuarioEntity encontrarIdUsuario(Long id){
            return usuarioRepository.findById(id).orElse(null);
        }

        public UsuarioEntity cadastrarUsuario(@Valid UsuarioRequestDTO usuarioRequestDTO){
                UsuarioEntity usuario = new UsuarioEntity();

                usuario.setNome(usuarioRequestDTO.getNome());
                usuario.setEmail(usuarioRequestDTO.getEmail());
                usuario.setTelefone(usuarioRequestDTO.getTelefone());
                usuario.setDataNascimento(usuarioRequestDTO.getData_nascimento());

                return usuarioRepository.save(usuario);
        }

        public List<UsuarioEntity> listarUsuarios(){
            return usuarioRepository.findAll();
        }

        public UsuarioEntity buscarUsuarioPorId(Long id){
           UsuarioEntity usuarioEncontrado = encontrarIdUsuario(id);

           if (usuarioEncontrado != null){
                return usuarioEncontrado;
           }
           throw new UsuarioNaoEncontradoException();
        }

        public List<UsuarioEntity> buscarUsuarioPeloNome(String nome) {
            return usuarioRepository.findByNomeContainingIgnoreCase(nome);
        }

        public UsuarioEntity atualizarUsuario(Long id, UsuarioEntity usuarioEntity){
            UsuarioEntity usuarioEncontrado = encontrarIdUsuario(id);

            if (usuarioEncontrado != null){
                usuarioEncontrado.setNome(usuarioEntity.getNome());
                usuarioEncontrado.setEmail(usuarioEntity.getEmail());
                usuarioEncontrado.setTelefone(usuarioEntity.getTelefone());
                usuarioEncontrado.setDataNascimento(usuarioEntity.getDataNascimento());

                return usuarioRepository.save(usuarioEncontrado);
            }
            throw new UsuarioNaoEncontradoException();
            }

        public void deletarUsuario(Long id){
            UsuarioEntity usuarioEncontrado = encontrarIdUsuario(id);

            if (usuarioEncontrado != null){
                usuarioRepository.delete(usuarioEncontrado);
            } else {
                throw new UsuarioNaoEncontradoException();
            }
        }
    }
