package com.attornatus.gerpessoaapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pessoa {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
    private List<Endereco> enderecos;

    public boolean removerEndereco(Endereco endereco) {
        return getEnderecos().remove(endereco);
    }

    public boolean adicionarEndereco(Endereco endereco) {
        return getEnderecos().add(endereco);
    }

    public List<Endereco> enderecoPrincipal() {
        return  this.getEnderecos().stream().filter(endereco -> endereco.getPrincipal().equals(Boolean.TRUE))
                .collect(Collectors.toList());
    }

}
