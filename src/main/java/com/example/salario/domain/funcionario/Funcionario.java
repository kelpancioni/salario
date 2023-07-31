package com.example.salario.domain.funcionario;

import com.example.salario.controllers.data.FuncionarioDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "funcionario")
@Entity(name = "funcionario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String endereco;
    private double salario;

    public static Funcionario fromRequestDTO(FuncionarioDTO data) {
        return getFuncionario(data);
    }

    public static Funcionario fromResponseDTO(FuncionarioDTO data) {
        Funcionario funcionario = getFuncionario(data);
        funcionario.id = data.getId();
        return funcionario;
    }

    private static Funcionario getFuncionario(FuncionarioDTO data) {
        Funcionario funcionario = new Funcionario();
        funcionario.nome = data.getNome();
        funcionario.cpf = data.getCpf();
        funcionario.dataNascimento = data.getDataNascimento();
        funcionario.endereco = data.getEndereco();
        funcionario.salario = data.getSalario();
        return funcionario;
    }
}
