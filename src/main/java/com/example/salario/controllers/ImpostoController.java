package com.example.salario.controllers;

import com.example.salario.domain.imposto.dto.ImpostoResponseDTO;
import com.example.salario.services.ImpostoService;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/imposto")
@Validated
public class ImpostoController {

    @Autowired
    private ImpostoService impostoService;

    @GetMapping("/check/{cpf}")
    public ImpostoResponseDTO checkImposto(@Valid @CPF @PathVariable(value = "cpf") String cpf) {
        return impostoService.checkarImposto(cpf);
    }
}
