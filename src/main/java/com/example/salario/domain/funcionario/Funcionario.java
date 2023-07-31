package com.example.salario.domain.funcionario;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Table(name = "funcionario")
@Entity(name = "funcionario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @NotEmpty(message = "CPF não pode ser vazio")
    @CPF
    private String cpf;

    @NotNull
    private LocalDate dataNascimento;

    private String endereco;
    private double salario;

    public static Funcionario fromDTO(FuncionarioRequestDTO data) {
        Funcionario funcionario = new Funcionario();
        funcionario.nome = data.getNome();
        funcionario.cpf = data.getCpf();
        funcionario.dataNascimento = data.getDataNascimento();
        funcionario.endereco = data.getEndereco();
        funcionario.salario = data.getSalario();
        return funcionario;
    }

    public static Funcionario fromDTO(FuncionarioResponseDTO data) {
        Funcionario funcionario = new Funcionario();
        funcionario.id = data.getId();
        funcionario.nome = data.getNome();
        funcionario.cpf = data.getCpf();
        funcionario.dataNascimento = data.getDataNascimento();
        funcionario.endereco = data.getEndereco();
        funcionario.salario = data.getSalario();
        return funcionario;
    }
}
