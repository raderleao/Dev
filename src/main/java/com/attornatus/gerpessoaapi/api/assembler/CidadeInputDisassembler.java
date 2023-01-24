package com.attornatus.gerpessoaapi.api.assembler;

import com.attornatus.gerpessoaapi.api.model.input.CidadeInput;
import com.attornatus.gerpessoaapi.domain.model.Cidade;
import com.attornatus.gerpessoaapi.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cidade toDomainObject(CidadeInput cidadeInput) {
        return modelMapper.map(cidadeInput, Cidade.class);
    }

    public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
        cidade.setEstado(new Estado());
        modelMapper.map(cidadeInput, cidade);
    }

}
