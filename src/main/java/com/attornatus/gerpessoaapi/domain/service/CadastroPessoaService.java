package com.attornatus.gerpessoaapi.domain.service;

import com.attornatus.gerpessoaapi.domain.exception.EntidadeEmUsoException;
import com.attornatus.gerpessoaapi.domain.exception.PessoaNaoEncontradaException;
import com.attornatus.gerpessoaapi.domain.model.Pessoa;
import com.attornatus.gerpessoaapi.domain.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroPessoaService {
    private static final String MSG_PESSOA_EM_USO = "Pessoa de código %d não pode ser removida, pois está em uso";

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Transactional
    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public void excluir(Long pessoaId) {
        try {
            pessoaRepository.deleteById(pessoaId);

        } catch (EmptyResultDataAccessException e) {
            throw new PessoaNaoEncontradaException(pessoaId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_PESSOA_EM_USO, pessoaId));
        }
    }


    public Pessoa buscarOuFalhar(Long pessoaId) {
        return pessoaRepository.findById(pessoaId).orElseThrow(() -> new PessoaNaoEncontradaException(pessoaId));
    }


}