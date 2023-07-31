package com.example.salario.domain.funcionario;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FuncionarioImpostoDTO {
    private String cpf;
    private String imposto;
}
