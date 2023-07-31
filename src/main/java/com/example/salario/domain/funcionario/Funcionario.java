package com.example.salario.domain.funcionario;

import com.example.salario.domain.funcionario.dto.FuncionarioDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
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
    @Past
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
