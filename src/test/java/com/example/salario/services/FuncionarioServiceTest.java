package com.example.salario.services;

import com.example.salario.controllers.data.FuncionarioRequestDTO;
import com.example.salario.controllers.data.FuncionarioRequestDTOBuilder;
import com.example.salario.controllers.data.FuncionarioResponseDTO;
import com.example.salario.domain.funcionario.Funcionario;
import com.example.salario.repositories.FuncionarioRepository;
import com.example.salario.services.impl.FuncionarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository repositoryMock;

    private FuncionarioService funcionarioService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        funcionarioService = new FuncionarioServiceImpl(repositoryMock);
    }

    @Test
    public void getAllFuncionarios_shouldReturnListOfFuncionarios() {
        // ARRANGE
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(getDefaultFuncionario());

        when(repositoryMock.findAll()).thenReturn(funcionarios);

        // ACTION
        List<FuncionarioResponseDTO> result = funcionarioService.getAllFuncionarios();

        // ASSERT
        assertEquals(1, result.size());
    }

    @Test
    public void getAllFuncionarios_shouldNotReturnListOfFuncionarios() {
        // ARRANGE
        List<Funcionario> funcionarios = new ArrayList<>();

        when(repositoryMock.findAll()).thenReturn(funcionarios);

        // ACTION
        List<FuncionarioResponseDTO> result = funcionarioService.getAllFuncionarios();

        // ASSERT
        assertEquals(0, result.size());
    }

    @Test
    public void criarFuncionario_shouldCreateFuncionario_whenCpfNotExists() {
        // ARRANGE
        FuncionarioRequestDTO funcionario = new FuncionarioRequestDTOBuilder().createFuncionarioDTO();

        when(repositoryMock.findFuncionarioByCpf(any())).thenReturn(null);
        when(repositoryMock.save(any())).thenReturn(getDefaultFuncionario());

        // ACTION
        FuncionarioResponseDTO result = funcionarioService.criarFuncionario(funcionario);

        // ASSERT
        assertNotNull(result);
        assertEquals(result.getCpf(), "43618207328");
        assertEquals(result.getNome(), "Tomás Miguel da Mota");
        assertEquals(result.getDataNascimento(), LocalDate.parse("1972-05-08"));
        assertEquals(result.getEndereco(), "Rua Sorocaba");
        assertEquals(result.getSalario(), 1080.0);
    }

    @Test
    void criarFuncionario_shouldNotCreateFuncionario_whenCpfExists() {
        // ARRANGE
        FuncionarioRequestDTO funcionario = new FuncionarioRequestDTOBuilder().createFuncionarioDTO();

        when(repositoryMock.findFuncionarioByCpf(any())).thenReturn(Funcionario.fromRequestDTO(funcionario));

        // ACTION
        // ASSERT
        assertThrows(EntityExistsException.class, () -> funcionarioService.criarFuncionario(funcionario));
    }

    @Test
    void getFuncionarioByCpf_shouldReturnFuncionario() {
        // ARRANGE
        Funcionario funcionario = getDefaultFuncionario();

        when(repositoryMock.findFuncionarioByCpf(any())).thenReturn(funcionario);
        // ACTION
        FuncionarioResponseDTO result = funcionarioService.getFuncionarioByCpf(funcionario.getCpf());

        // ASSERT
        assertNotNull(result);
        assertEquals(Funcionario.fromResponseDTO(result), funcionario);
    }

    @Test
    void getFuncionarioByCpf_shouldNotReturnFuncionario() {
        // ARRANGE
        when(repositoryMock.findFuncionarioByCpf(any())).thenReturn(null);
        // ACTION
        // ASSERT
        assertThrows(EntityNotFoundException.class, () -> funcionarioService.getFuncionarioByCpf(any()));
    }

    @Test
    void updateFuncionario_shouldReturnFuncionario() {
        // ARRANGE
        FuncionarioResponseDTO funcionarioResponseDTO = new FuncionarioResponseDTO(getDefaultFuncionario());
        when(repositoryMock.save(any())).thenReturn(getDefaultFuncionario());

        // ACTION
        FuncionarioResponseDTO result = funcionarioService.updateFuncionario(funcionarioResponseDTO);

        // ASSERT
        assertNotNull(result);
        verify(repositoryMock).save(any());
    }

    private Funcionario getDefaultFuncionario() {
        FuncionarioResponseDTO funcionarioDTO = new FuncionarioResponseDTO();
        funcionarioDTO.setId(3L);
        funcionarioDTO.setNome("Tomás Miguel da Mota");
        funcionarioDTO.setCpf("43618207328");
        funcionarioDTO.setDataNascimento(LocalDate.parse("1972-05-08"));
        funcionarioDTO.setEndereco("Rua Sorocaba");
        funcionarioDTO.setSalario(1080.0);

        return Funcionario.fromResponseDTO(funcionarioDTO);
    }
}
