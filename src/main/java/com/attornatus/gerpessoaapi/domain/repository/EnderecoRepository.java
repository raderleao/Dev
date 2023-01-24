package com.attornatus.gerpessoaapi.domain.repository;

import com.attornatus.gerpessoaapi.domain.model.Endereco;
import com.attornatus.gerpessoaapi.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("from Endereco where pessoa.id = :pessoa and id = :endereco")
    Optional<Endereco> findById(@Param("pessoa") Long pessoaId,
                               @Param("endereco") Long enderecoId);

    @Query("from Endereco where pessoa.id = :pessoa and id = :endereco and principal = true")
    Optional<Endereco> findPrincipalById(@Param("pessoa") Long pessoaId,
                                @Param("endereco") Long enderecoId);

    @Query("from Endereco where pessoa.id = :pessoa")
    List<Endereco> findEnderecosByPessoa(@Param("pessoa") Long pessoaId);
}
