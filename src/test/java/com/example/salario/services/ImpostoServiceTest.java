package com.example.salario.services;

import com.example.salario.services.impl.FuncionarioServiceImpl;
import com.example.salario.services.impl.ImpostoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ImpostoServiceTest {

    @Mock
    private FuncionarioServiceImpl funcionarioServiceMock;

    private ImpostoServiceImpl impostoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        impostoService = new ImpostoServiceImpl(funcionarioServiceMock);
    }
}
