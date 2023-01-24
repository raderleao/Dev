package com.attornatus.gerpessoaapi.api.assembler;

import com.attornatus.gerpessoaapi.api.model.input.CidadeInput;
import com.attornatus.gerpessoaapi.api.model.input.EnderecoInput;
import com.attornatus.gerpessoaapi.domain.model.Cidade;
import com.attornatus.gerpessoaapi.domain.model.Endereco;
import com.attornatus.gerpessoaapi.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Endereco toDomainObject(EnderecoInput enderecoInput) {
        return modelMapper.map(enderecoInput, Endereco.class);
    }

    public void copyToDomainObject(EnderecoInput enderecoInput, Endereco endereco) {
        endereco.setCidade(new Cidade());
        modelMapper.map(enderecoInput, endereco);
    }

}
