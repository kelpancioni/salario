package com.example.salario.services.impl;

import com.example.salario.domain.funcionario.Funcionario;
import com.example.salario.controllers.data.FuncionarioRequestDTO;
import com.example.salario.controllers.data.FuncionarioResponseDTO;
import com.example.salario.repositories.FuncionarioRepository;
import com.example.salario.services.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository repository;

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
        Funcionario funcionarioEntity = Funcionario.fromResponseDTO(funcionario);
        Funcionario savedFuncionario = repository.save(funcionarioEntity);
        return new FuncionarioResponseDTO(savedFuncionario);
    }

}
