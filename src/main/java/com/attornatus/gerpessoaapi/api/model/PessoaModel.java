package com.attornatus.gerpessoaapi.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PessoaModel {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;

}
