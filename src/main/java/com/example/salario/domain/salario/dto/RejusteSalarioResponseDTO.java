package com.example.salario.domain.salario.dto;

import lombok.Getter;

@Getter
public class RejusteSalarioResponseDTO {
    private String cpf;
    private double novoSalario;
    private double valorReajuste;
    private String percentualReajuste;

    public RejusteSalarioResponseDTO(String cpf, double novoSalario, double valorReajuste, double percentualReajuste) {
        this.cpf = cpf;
        this.novoSalario = novoSalario;
        this.valorReajuste = valorReajuste;
        this.percentualReajuste = String.format("%.0f",percentualReajuste * 100) + " %";
    }
}
