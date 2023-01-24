package com.attornatus.gerpessoaapi.api.assembler;

import com.attornatus.gerpessoaapi.api.model.CidadeModel;
import com.attornatus.gerpessoaapi.api.model.PessoaModel;
import com.attornatus.gerpessoaapi.domain.model.Cidade;
import com.attornatus.gerpessoaapi.domain.model.Pessoa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PessoaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PessoaModel toModel(Pessoa pessoa) {

        return modelMapper.map(pessoa, PessoaModel.class);
    }

    public List<PessoaModel> toCollectionModel(List<Pessoa> pessoas) {
        return pessoas.stream()
                .map(pessoa -> toModel(pessoa))
                .collect(Collectors.toList());
    }
}
