package com.biblioteca.exception;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException(){
        super("Usuario não cadastrado no sistema");
    }
}
