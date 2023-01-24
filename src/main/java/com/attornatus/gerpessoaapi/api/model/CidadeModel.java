package com.attornatus.gerpessoaapi.api.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeModel {

    private Long id;
    private String nome;
    private EstadoModel estado;

    public void setEstado(String value) {
    }
}
