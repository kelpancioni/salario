package com.example.salario.domain.funcionario;

import lombok.Getter;

@Getter
public class FuncionarioRejusteSalarioDTO {
    private String cpf;
    private double novoSalario;
    private double valorReajuste;
    private String percentualReajuste;

    public FuncionarioRejusteSalarioDTO(String cpf, double novoSalario, double valorReajuste, double percentualReajuste) {
        this.cpf = cpf;
        this.novoSalario = novoSalario;
        this.valorReajuste = valorReajuste;
        this.percentualReajuste = String.format("%.0f",percentualReajuste * 100) + " %";
    }
}
