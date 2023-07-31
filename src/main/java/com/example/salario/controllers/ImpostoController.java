package com.example.salario.controllers;

import com.example.salario.controllers.data.ImpostoResponseDTO;

import com.example.salario.services.ImpostoService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/imposto")
public class ImpostoController {

    private final ImpostoService impostoService;

    @GetMapping("/check/{cpf}")
    public ImpostoResponseDTO checkImposto(@Valid @CPF @PathVariable(value = "cpf") String cpf) {
        return impostoService.checkarImposto(cpf);
    }
}
