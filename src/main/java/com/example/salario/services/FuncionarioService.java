package com.example.salario.services;

import com.example.salario.domain.funcionario.*;
import com.example.salario.repositories.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    public List<FuncionarioResponseDTO> getAllFuncionarios() {
        List<FuncionarioResponseDTO> funcionarioList = repository.findAll().stream().map(FuncionarioResponseDTO::new).collect(Collectors.toList());
        return funcionarioList;
    }

    public void criarFuncionario(FuncionarioRequestDTO data) {
        Funcionario funcionarioData = Funcionario.fromDTO(data);
        repository.save(funcionarioData);
    }

    public FuncionarioResponseDTO getFuncionarioByCpf(String cpf) {
        Funcionario funcionario = repository.findFuncionarioByCpf(cpf);
        if (funcionario == null) {
            throw new EntityNotFoundException();
        }
        return new FuncionarioResponseDTO(funcionario);
    }

    public FuncionarioRejusteSalarioDTO reajustarSalario(String cpf) {
        FuncionarioResponseDTO funcionario = this.getFuncionarioByCpf(cpf);
        FuncionarioRejusteSalarioDTO aumentoCalculado = this.calcularAumento(funcionario);

        funcionario.setSalario(aumentoCalculado.getNovoSalario());
        repository.save(Funcionario.fromDTO(funcionario));

        return aumentoCalculado;
    }

    private FuncionarioRejusteSalarioDTO calcularAumento(FuncionarioResponseDTO funcionario) {

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

        valorReajuste = salario * percentualReajuste;
        novoSalario = salario + valorReajuste;

        return new FuncionarioRejusteSalarioDTO(cpf, novoSalario, valorReajuste, percentualReajuste);
    }

    public FuncionarioImpostoDTO checkarImposto(String cpf) {
        FuncionarioResponseDTO funcionario = this.getFuncionarioByCpf(cpf);
        double salario = funcionario.getSalario();
        return new FuncionarioImpostoDTO(cpf, this.calcularImposto(salario));
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
