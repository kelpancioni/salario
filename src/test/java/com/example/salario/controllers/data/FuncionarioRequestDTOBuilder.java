package com.example.salario.controllers.data;

public class FuncionarioRequestDTOBuilder extends FuncionarioDTOBuilder {

    public FuncionarioRequestDTO createFuncionarioDTO() {
        FuncionarioRequestDTO funcionarioRequestDTO = new FuncionarioRequestDTO();
        funcionarioRequestDTO.setNome(nome);
        funcionarioRequestDTO.setCpf(cpf);
        funcionarioRequestDTO.setDataNascimento(dataNascimento);
        funcionarioRequestDTO.setEndereco(endereco);
        funcionarioRequestDTO.setSalario(salario);

        return funcionarioRequestDTO;
    }
}
