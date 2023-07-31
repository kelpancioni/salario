package com.example.salario.services;

import com.example.salario.controllers.data.RejusteSalarioResponseDTO;

public interface SalarioService {
    RejusteSalarioResponseDTO reajustarSalario(String cpf);
}
