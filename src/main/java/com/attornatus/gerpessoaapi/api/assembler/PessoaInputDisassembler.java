package com.attornatus.gerpessoaapi.api.assembler;

import com.attornatus.gerpessoaapi.api.model.input.PessoaInput;
import com.attornatus.gerpessoaapi.domain.model.Endereco;
import com.attornatus.gerpessoaapi.domain.model.Pessoa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PessoaInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Pessoa toDomainObject(PessoaInput pessoaInput) {

        return modelMapper.map(pessoaInput, Pessoa.class);
    }

    public void copyToDomainObject(PessoaInput pessoaInput, Pessoa pessoa) {
        modelMapper.map(pessoaInput, pessoa);
    }

}
