package com.example.salario.controllers;

import com.example.salario.domain.salario.dto.RejusteSalarioResponseDTO;
import com.example.salario.services.SalarioService;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/salario")
@Validated
public class SalarioController {
    
    @Autowired
    private SalarioService salarioService;

    @PostMapping("/reajuste/{cpf}")
    public RejusteSalarioResponseDTO reajusteSalario(@Valid @CPF @PathVariable(value = "cpf") String cpf) {
        return salarioService.reajustarSalario(cpf);
    }
}
