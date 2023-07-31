package com.example.salario.controllers;

import com.example.salario.controllers.data.FuncionarioRequestDTO;
import com.example.salario.controllers.data.FuncionarioResponseDTO;
import com.example.salario.services.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping
    public List<FuncionarioResponseDTO> buscarFuncionarios() {
        return funcionarioService.getAllFuncionarios();
    }

    @GetMapping("/{cpf}")
    public FuncionarioResponseDTO buscarFuncionarioPorCpf(@Valid @CPF @PathVariable(value = "cpf") String cpf) {
        return funcionarioService.getFuncionarioByCpf(cpf);
    }

    @PostMapping
    public void criarFuncionario(@RequestBody @Valid FuncionarioRequestDTO data) {
        funcionarioService.criarFuncionario(data);
    }

}
