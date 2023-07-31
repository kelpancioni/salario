package com.example.salario.services;

import com.example.salario.domain.funcionario.dto.FuncionarioResponseDTO;
import com.example.salario.domain.salario.dto.RejusteSalarioResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SalarioService {

    @Autowired
    private FuncionarioService funcionarioService;

    public RejusteSalarioResponseDTO reajustarSalario(String cpf) {
        FuncionarioResponseDTO funcionario = funcionarioService.getFuncionarioByCpf(cpf);
        RejusteSalarioResponseDTO aumentoCalculado = this.calcularAumento(funcionario);
        funcionario.setSalario(aumentoCalculado.getNovoSalario());
        funcionarioService.updateFuncionario(funcionario);

        return aumentoCalculado;
    }

    private RejusteSalarioResponseDTO calcularAumento(FuncionarioResponseDTO funcionario) {

        double novoSalario;
        double valorReajuste;
        double percentualReajuste;
        double salario = funcionario.getSalario();
        String cpf = funcionario.getCpf();

        if (salario <= 400) {
            percentualReajuste = 0.15;
        } else if (salario <= 800) {
            percentualReajuste = 0.12;
        } else if (salario <= 1200) {
            percentualReajuste = 0.10;
        } else if (salario <= 2000) {
            percentualReajuste = 0.07;
        } else {
            percentualReajuste = 0.04;
        }

        valorReajuste = new BigDecimal(salario * percentualReajuste).setScale(2, RoundingMode.HALF_UP).doubleValue();
        novoSalario = new BigDecimal(salario + valorReajuste).setScale(2, RoundingMode.HALF_UP).doubleValue();

        return new RejusteSalarioResponseDTO(cpf, novoSalario, valorReajuste, percentualReajuste);
    }
}
