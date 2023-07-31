package com.example.salario.controllers.data;

public class FuncionarioResponseDTOBuilder extends FuncionarioDTOBuilder {

    private Long id = 3L;
    public FuncionarioResponseDTO createFuncionarioResponseDTO() {
        FuncionarioResponseDTO funcionarioResponseDTO = new FuncionarioResponseDTO();
        funcionarioResponseDTO.setId(id);
        funcionarioResponseDTO.setNome(nome);
        funcionarioResponseDTO.setCpf(cpf);
        funcionarioResponseDTO.setDataNascimento(dataNascimento);
        funcionarioResponseDTO.setEndereco(endereco);
        funcionarioResponseDTO.setSalario(salario);

        return funcionarioResponseDTO;
    }
}
