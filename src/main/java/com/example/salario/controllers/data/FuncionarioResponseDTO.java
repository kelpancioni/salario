package com.example.salario.controllers.data;

import com.example.salario.domain.funcionario.Funcionario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FuncionarioResponseDTO extends FuncionarioDTO {
    public FuncionarioResponseDTO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.cpf = funcionario.getCpf();
        this.dataNascimento = funcionario.getDataNascimento();
        this.endereco = funcionario.getEndereco();
        this.salario = funcionario.getSalario();
    }
}
