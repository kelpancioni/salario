package com.example.salario.domain.imposto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImpostoResponseDTO {
    private String cpf;
    private String imposto;
}
