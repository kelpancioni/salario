package com.example.salario.controllers.data;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class FuncionarioDTO {
    @NotBlank(message = "Nome não pode ser vazio")
    protected String nome;

    @NotBlank(message = "CPF não pode ser vazio")
    @CPF
    protected String cpf;

    @NotNull
    protected LocalDate dataNascimento;

    protected String endereco;

    @NotNull
    protected double salario;
}
