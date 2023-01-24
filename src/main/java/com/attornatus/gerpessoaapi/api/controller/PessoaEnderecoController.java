package com.attornatus.gerpessoaapi.api.controller;

import com.attornatus.gerpessoaapi.api.assembler.EnderecoInputDisassembler;
import com.attornatus.gerpessoaapi.api.assembler.EnderecoModelAssembler;
import com.attornatus.gerpessoaapi.api.assembler.PessoaInputDisassembler;
import com.attornatus.gerpessoaapi.api.assembler.PessoaModelAssembler;
import com.attornatus.gerpessoaapi.api.model.EnderecoModel;
import com.attornatus.gerpessoaapi.api.model.PessoaModel;
import com.attornatus.gerpessoaapi.api.model.input.EnderecoInput;
import com.attornatus.gerpessoaapi.api.model.input.PessoaInput;
import com.attornatus.gerpessoaapi.domain.exception.CidadeNaoEncontradaException;
import com.attornatus.gerpessoaapi.domain.exception.NegocioException;
import com.attornatus.gerpessoaapi.domain.exception.PessoaNaoEncontradaException;
import com.attornatus.gerpessoaapi.domain.model.Endereco;
import com.attornatus.gerpessoaapi.domain.model.Pessoa;
import com.attornatus.gerpessoaapi.domain.repository.EnderecoRepository;
import com.attornatus.gerpessoaapi.domain.repository.PessoaRepository;
import com.attornatus.gerpessoaapi.domain.service.CadastroEnderecoService;
import com.attornatus.gerpessoaapi.domain.service.CadastroPessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoas/{pessoaId}/enderecos")
public class PessoaEnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CadastroEnderecoService cadastroEndereco;

   @Autowired
   private CadastroPessoaService cadastroPessoa;

   @Autowired
   private EnderecoModelAssembler enderecoModelAssembler;

   @Autowired
   private EnderecoInputDisassembler enderecoInputDisassembler;

   @Autowired
   private PessoaInputDisassembler pessoaInputDisassembler;

    @GetMapping
    public List<EnderecoModel> listar(@PathVariable Long pessoaId) {
        Pessoa pessoa = cadastroPessoa.buscarOuFalhar(pessoaId);

        List<Endereco> todosEnderecos = cadastroEndereco.buscarEnderecos(pessoaId);

        return enderecoModelAssembler.toCollectionModel(todosEnderecos);
    }

    @GetMapping("/{enderecoId}")
    public EnderecoModel buscar(@PathVariable Long pessoaId, @PathVariable Long enderecoId) {
        Endereco endereco = cadastroEndereco.buscarOuFalhar(pessoaId, enderecoId);

        return enderecoModelAssembler.toModel(endereco);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoModel adicionar(@PathVariable Long pessoaId,
                                  @RequestBody @Valid EnderecoInput enderecoInput) {
        Pessoa pessoa = cadastroPessoa.buscarOuFalhar(pessoaId);

        Endereco endereco = enderecoInputDisassembler.toDomainObject(enderecoInput);
        endereco.setPessoa(pessoa);

        endereco = cadastroEndereco.salvar(endereco);

        return enderecoModelAssembler.toModel(endereco);
    }

    @PutMapping("/{enderecoId}")
    public EnderecoModel atualizar(@PathVariable Long pessoaId, @PathVariable Long enderecoId,
                                  @RequestBody @Valid EnderecoInput enderecoInput) {
        Endereco enderecoAtual = cadastroEndereco.buscarOuFalhar(pessoaId, enderecoId);

        enderecoInputDisassembler.copyToDomainObject(enderecoInput, enderecoAtual);

        enderecoAtual = cadastroEndereco.salvar(enderecoAtual);

        return enderecoModelAssembler.toModel(enderecoAtual);
    }

    @PutMapping("/{enderecoId}/principal")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionarPrincipal(@PathVariable Long pessoaId, @PathVariable Long enderecoId) {

        cadastroEndereco.adicionarComoPrincipal(pessoaId, enderecoId);
    }


}
