package com.example.salario.domain.funcionario.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class FuncionarioDTO {
    protected Long id;
    protected String nome;

    @NotNull
    protected String cpf;

    @NotNull
    protected LocalDate dataNascimento;
    protected String endereco;
    protected double salario;
}
