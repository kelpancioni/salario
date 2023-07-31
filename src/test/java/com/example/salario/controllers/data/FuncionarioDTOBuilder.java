package com.example.salario.controllers.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTOBuilder {
    protected String nome = "Tomás Miguel da Mota";
    protected String cpf = "43618207328";
    protected LocalDate dataNascimento = LocalDate.of(1972,5,8);
    protected String endereco = "Rua Sorocaba";
    protected double salario = 1080.0;
}
