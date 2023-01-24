package com.attornatus.gerpessoaapi.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Endereco {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String logradouro;

    @Column
    private String cep;

    @Column
    private String numero;

    @Column
    private String bairro;

    @Column
    private String complemento;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @Column
    private Boolean principal;

    public void adicionarComoPrincipal() {
        setPrincipal(true);
    }

    public void removerComoPrincipal() {
        setPrincipal(false);
    }


}
