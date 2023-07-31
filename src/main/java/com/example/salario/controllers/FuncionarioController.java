package com.example.salario.controllers;

import com.example.salario.domain.funcionario.dto.FuncionarioRequestDTO;
import com.example.salario.domain.funcionario.dto.FuncionarioResponseDTO;
import com.example.salario.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<FuncionarioResponseDTO> buscarFuncionarios() {
        return funcionarioService.getAllFuncionarios();
    }

    @GetMapping("/{cpf}")
    public FuncionarioResponseDTO buscarFuncionarioPorCpf(@PathVariable(value = "cpf") String cpf) {
        return funcionarioService.getFuncionarioByCpf(cpf);
    }

    @PostMapping
    public void criarFuncionario(@RequestBody @Valid FuncionarioRequestDTO data) {
        funcionarioService.criarFuncionario(data);
    }

}
