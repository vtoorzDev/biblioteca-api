package com.biblioteca.exception;

public class LivroNaoEncontradoException extends RuntimeException{
    public LivroNaoEncontradoException(){
        super("Livro não encontrado no sistema");
    }
}
