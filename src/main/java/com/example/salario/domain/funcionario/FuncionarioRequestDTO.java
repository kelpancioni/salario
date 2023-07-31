package com.example.salario.domain.funcionario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.NotNull;


@Getter
@AllArgsConstructor
public class FuncionarioRequestDTO {

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @NotEmpty(message = "CPF não pode ser vazio")
    @CPF
    private String cpf;

    @NotNull
    private LocalDate dataNascimento;
    private String endereco;
    private double salario;

}
