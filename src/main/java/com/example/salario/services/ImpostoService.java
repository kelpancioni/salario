package com.example.salario.services;

import com.example.salario.controllers.data.ImpostoResponseDTO;

public interface ImpostoService {
    ImpostoResponseDTO checkarImposto(String cpf);
}
