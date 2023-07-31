package com.example.salario.services;

import com.example.salario.controllers.data.FuncionarioRequestDTO;
import com.example.salario.controllers.data.FuncionarioResponseDTO;

import java.util.List;

public interface FuncionarioService {

    List<FuncionarioResponseDTO> getAllFuncionarios();
    FuncionarioResponseDTO criarFuncionario(FuncionarioRequestDTO data);
    FuncionarioResponseDTO getFuncionarioByCpf(String cpf);
    FuncionarioResponseDTO updateFuncionario(FuncionarioResponseDTO funcionario);
}
