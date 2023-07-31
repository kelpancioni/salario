package com.example.salario.services.impl;

import com.example.salario.controllers.data.FuncionarioResponseDTO;
import com.example.salario.controllers.data.RejusteSalarioResponseDTO;
import com.example.salario.services.FuncionarioService;
import com.example.salario.services.SalarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class SalarioServiceImpl implements SalarioService {

    private final FuncionarioService funcionarioService;

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
