package com.example.salario.services;

import com.example.salario.services.impl.FuncionarioServiceImpl;
import com.example.salario.services.impl.SalarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SalarioServiceTest {

    @Mock
    private FuncionarioServiceImpl funcionarioServiceMock;

    private SalarioServiceImpl salarioService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        salarioService = new SalarioServiceImpl(funcionarioServiceMock);
    }
}
