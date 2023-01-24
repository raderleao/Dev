package com.attornatus.gerpessoaapi.domain.service;

import com.attornatus.gerpessoaapi.domain.exception.EnderecoPessoaNaoEncontradaException;
import com.attornatus.gerpessoaapi.domain.model.Cidade;
import com.attornatus.gerpessoaapi.domain.model.Endereco;
import com.attornatus.gerpessoaapi.domain.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroEnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Transactional
    public Endereco salvar(Endereco endereco) {
        Long pessoaId = endereco.getPessoa().getId();
        List<Endereco> enderecos = buscarEnderecos(pessoaId);

        if(enderecos.isEmpty()){
            endereco.setPrincipal(Boolean.TRUE);
        } else {
            endereco.setPrincipal(Boolean.FALSE);
        }

        Long cidadeId = endereco.getCidade().getId();
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

        endereco.setCidade(cidade);

        return enderecoRepository.save(endereco);
    }

    @Transactional
    public void adicionarComoPrincipal(Long pessoaId, Long enderecoId){
        buscarEnderecos(pessoaId)
                .stream()
                .forEach(endereco -> endereco.removerComoPrincipal());

        buscarOuFalhar(pessoaId, enderecoId)
                .adicionarComoPrincipal();;
    }

    public Endereco buscarOuFalhar(Long pessoaId, Long enderecoId) {
        return enderecoRepository.findById(pessoaId, enderecoId)
                .orElseThrow(() -> new EnderecoPessoaNaoEncontradaException(pessoaId, enderecoId));
    }

    public List<Endereco> buscarEnderecos(Long pessoaId) {
        List<Endereco> todosEnderecos = enderecoRepository.findEnderecosByPessoa(pessoaId);
        return todosEnderecos;
    }

}