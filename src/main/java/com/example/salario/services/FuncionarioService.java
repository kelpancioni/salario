package com.example.salario.services;

import com.example.salario.domain.funcionario.Funcionario;
import com.example.salario.domain.funcionario.dto.FuncionarioRequestDTO;
import com.example.salario.domain.funcionario.dto.FuncionarioResponseDTO;
import com.example.salario.domain.imposto.dto.ImpostoResponseDTO;
import com.example.salario.domain.salario.dto.RejusteSalarioResponseDTO;
import com.example.salario.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
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

    public FuncionarioResponseDTO criarFuncionario(FuncionarioRequestDTO data) {

        Funcionario funcionario = repository.findFuncionarioByCpf(data.getCpf());
        if (funcionario != null) {
           throw new EntityExistsException();
        }
        return new FuncionarioResponseDTO(repository.save(Funcionario.fromRequestDTO(data)));
    }

    public FuncionarioResponseDTO getFuncionarioByCpf(String cpf) {
        Funcionario funcionario = repository.findFuncionarioByCpf(cpf);
        if (funcionario == null) {
            throw new EntityNotFoundException();
        }
        return new FuncionarioResponseDTO(funcionario);
    }

    public FuncionarioResponseDTO updateFuncionario(FuncionarioResponseDTO funcionario) {
        return new FuncionarioResponseDTO(repository.save(Funcionario.fromResponseDTO(funcionario)));
    }

}
