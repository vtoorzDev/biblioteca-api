package com.biblioteca.exception;

public class LivroNaoDisponivelException extends RuntimeException{
    public LivroNaoDisponivelException(){
        super("Livro não disponivel");
    }
}
