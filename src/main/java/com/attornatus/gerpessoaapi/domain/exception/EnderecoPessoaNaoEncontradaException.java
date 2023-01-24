package com.attornatus.gerpessoaapi.domain.exception;

public class EnderecoPessoaNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public EnderecoPessoaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public EnderecoPessoaNaoEncontradaException(Long pessoaId, Long enderecoId) {
        this(String.format("Não existe um cadastro de endereço com código %d para a pessoa de código %d",
                enderecoId, pessoaId));
    }
}
