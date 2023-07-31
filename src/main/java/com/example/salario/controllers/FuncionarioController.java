package com.example.salario.controllers;

import com.example.salario.domain.funcionario.FuncionarioImpostoDTO;
import com.example.salario.domain.funcionario.FuncionarioRejusteSalarioDTO;
import com.example.salario.domain.funcionario.FuncionarioRequestDTO;
import com.example.salario.domain.funcionario.FuncionarioResponseDTO;
import com.example.salario.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/{cpf}/reajuste")
    public FuncionarioRejusteSalarioDTO reajusteSalario(@PathVariable(value = "cpf") String cpf) {
        return funcionarioService.reajustarSalario(cpf);
    }

    @GetMapping("/{cpf}/imposto")
    public FuncionarioImpostoDTO checkarImposto(@PathVariable(value = "cpf") String cpf) {
        return funcionarioService.checkarImposto(cpf);
    }
}
