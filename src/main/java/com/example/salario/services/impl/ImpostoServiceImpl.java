package com.example.salario.services.impl;

import com.example.salario.controllers.data.FuncionarioResponseDTO;
import com.example.salario.controllers.data.ImpostoResponseDTO;
import com.example.salario.services.FuncionarioService;
import com.example.salario.services.ImpostoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class ImpostoServiceImpl implements ImpostoService {

    private final FuncionarioService funcionarioService;

    public ImpostoResponseDTO checkarImposto(String cpf) {
        FuncionarioResponseDTO funcionario = funcionarioService.getFuncionarioByCpf(cpf);
        double salario = funcionario.getSalario();
        return new ImpostoResponseDTO(cpf, this.calcularImposto(salario));
    }

    private String calcularImposto(double salario) {
        if (salario <= 2000.00) {
            return "Isento";
        } else {
            double impostoDevido = 0.00;
            if (salario <= 3000.00) {
                impostoDevido = (salario - 2000.00) * 0.08;
            } else {
                impostoDevido += (3000.00 - 2000.00) * 0.08;
                if (salario <= 4500.00) {
                    impostoDevido += (salario - 3000.00) * 0.18;
                } else {
                    impostoDevido += (4500.00 - 3000.00) * 0.18;
                    impostoDevido += (salario - 4500.00) * 0.28;
                }
            }
            BigDecimal impostoDevidoBigDecimal = new BigDecimal(impostoDevido).setScale(2, RoundingMode.HALF_UP);
            return "R$ " + String.format("%.2f", impostoDevidoBigDecimal);
        }
    }
}
