package com.attornatus.gerpessoaapi.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PessoaInput {

    @NotNull
    private String nome;

    @NotNull
    private LocalDate dataNascimento;

}