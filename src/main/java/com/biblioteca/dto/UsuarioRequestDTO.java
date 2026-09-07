package com.biblioteca.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioRequestDTO {
    @NotBlank(message = "O campo do nome precisa ser preenchido")
    @Size(min = 3, max = 100, message = "O campo do nome precisa ter de 3 a 100 caracteres")
    private String nome;

    @NotBlank(message = "O campo do email precisa ser preenchido")
    @Email(message = "Você precisa digitar no formato de email")
    @Size(min = 10, max = 200, message = "O campo de email precisa ter entre 10 e 200 caracteres")
    private String email;

    @NotBlank(message = "O campo de telefone precisa ser preenchido")
    @Size(min = 11, max = 11, message = "O campo de telefone precisa ter 11 digitos (contando o ddd)")
    private String telefone;

    @NotNull(message = "O campo de data de nascimento precisa ser preenchido")
    @Past(message = "A idade precisa ser válida")
    private LocalDate data_nascimento;

}

