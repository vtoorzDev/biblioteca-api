package com.biblioteca.exception;

public class IsbnDuplicadoException extends RuntimeException {

    public IsbnDuplicadoException(){
        super("já existe um livro com este ISBN");
    }
}
