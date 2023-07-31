package com.example.salario.controllers;

import com.example.salario.controllers.data.RejusteSalarioResponseDTO;
import com.example.salario.services.SalarioService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/salario")
public class SalarioController {

    private final SalarioService salarioService;

    @PostMapping("/reajuste/{cpf}")
    public RejusteSalarioResponseDTO reajusteSalario(@Valid @CPF @PathVariable(value = "cpf") String cpf) {
        return salarioService.reajustarSalario(cpf);
    }
}
