package com.example.salario.controllers;

import com.example.salario.domain.salario.dto.RejusteSalarioResponseDTO;
import com.example.salario.services.SalarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/salario")
public class SalarioController {
    
    @Autowired
    private SalarioService salarioService;

    @PostMapping("/reajuste/{cpf}")
    public RejusteSalarioResponseDTO reajusteSalario(@PathVariable(value = "cpf") String cpf) {
        return salarioService.reajustarSalario(cpf);
    }
}
