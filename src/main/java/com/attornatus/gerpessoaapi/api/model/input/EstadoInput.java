package com.attornatus.gerpessoaapi.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadoInput {

    @NotBlank
    private String nome;

}