package com.attornatus.gerpessoaapi;

import com.attornatus.gerpessoaapi.domain.model.Pessoa;
import com.attornatus.gerpessoaapi.domain.repository.PessoaRepository;
import com.attornatus.gerpessoaapi.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroPessoaIT {
    private static final int PESSOA_ID_INEXISTENTE = 100;

    private Pessoa pessoaBrunaLeao;
    private int quantidadePessoasCadastradas;
    private String jsonCorretoPessoa;

    @LocalServerPort
    private int port;

    @Autowired
    private PessoaRepository pessoaRepository;


    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/pessoas";

        prepararDados();

        jsonCorretoPessoa = ResourceUtils.getContentFromResource(
                "/json/correto/pessoa-rader-bruno-leao.json");

    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarPessoas() {
        given()
                .accept(ContentType.JSON)
           .when()
                .get()
           .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarQuantidadeCorretaDePessoas_QuandoConsultarPessoas () {
        given()
                .accept(ContentType.JSON)
           .when()
                .get()
           .then()
                .body("", hasSize(quantidadePessoasCadastradas));
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarPessoa() {
        given()
                .body(jsonCorretoPessoa)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
          .when()
                .post()
          .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarPessoaExistente() {
        given()
                .pathParam("pessoaId", pessoaBrunaLeao.getId())
                .accept(ContentType.JSON)
          .when()
                .get("/{pessoaId}")
          .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo(pessoaBrunaLeao.getNome()));
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarPessoaInexistente() {
        given()
                .pathParam("pessoaId", PESSOA_ID_INEXISTENTE)
                .accept(ContentType.JSON)
          .when()
                .get("/{pessoaId}")
          .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDados() {
        Pessoa pessoaRaderBrunoLeao = new Pessoa();
        pessoaRaderBrunoLeao.setNome("Rader Bruno Leao");
        pessoaRepository.save(pessoaRaderBrunoLeao);

        pessoaBrunaLeao = new Pessoa();
        pessoaBrunaLeao.setNome("Bruna Leao");
        pessoaRepository.save(pessoaBrunaLeao);

        quantidadePessoasCadastradas = (int) pessoaRepository.count();
    }

}
