package com.attornatus.gerpessoaapi.api.controller;

import com.attornatus.gerpessoaapi.api.assembler.PessoaInputDisassembler;
import com.attornatus.gerpessoaapi.api.assembler.PessoaModelAssembler;
import com.attornatus.gerpessoaapi.api.model.PessoaModel;
import com.attornatus.gerpessoaapi.api.model.input.PessoaInput;
import com.attornatus.gerpessoaapi.domain.exception.CidadeNaoEncontradaException;
import com.attornatus.gerpessoaapi.domain.exception.NegocioException;
import com.attornatus.gerpessoaapi.domain.exception.PessoaNaoEncontradaException;
import com.attornatus.gerpessoaapi.domain.model.Pessoa;
import com.attornatus.gerpessoaapi.domain.repository.PessoaRepository;
import com.attornatus.gerpessoaapi.domain.service.CadastroPessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CadastroPessoaService cadastroPessoa;

    @Autowired
    private PessoaInputDisassembler pessoaInputDisassembler;

    @Autowired
    private PessoaModelAssembler pessoaModelAssembler;

    @GetMapping
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @GetMapping("/{pessoaId}")
    public Pessoa buscar(@PathVariable Long pessoaId) {
        return cadastroPessoa.buscarOuFalhar(pessoaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaModel adicionar(@RequestBody @Valid PessoaInput pessoaInput) {

        try {
            Pessoa pessoa = pessoaInputDisassembler.toDomainObject(pessoaInput);
            return pessoaModelAssembler.toModel(cadastroPessoa.salvar(pessoa));
        } catch (CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{pessoaId}")
    public PessoaModel atualizar(@PathVariable Long pessoaId,
                                 @RequestBody @Valid PessoaInput pessoaInput) {

        try {
            Pessoa pessoaAtual = cadastroPessoa.buscarOuFalhar(pessoaId);
            pessoaInputDisassembler.copyToDomainObject(pessoaInput, pessoaAtual);

            return pessoaModelAssembler.toModel(cadastroPessoa.salvar(pessoaAtual));

        } catch (PessoaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{pessoaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long pessoaId) {
        cadastroPessoa.excluir(pessoaId);
    }

}
