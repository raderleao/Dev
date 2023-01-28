# API CADASTRO DE PESSOAS

## 🚀  ESCOPO

Usando Spring boot, crie uma API simples para gerenciar Pessoas. Esta API deve permitir:  
```
	•	Criar uma pessoa
	•	Editar uma pessoa
	•	Consultar uma pessoa
	•	Listar pessoas
	•	Criar endereço para pessoa
	•	Listar endereços da pessoa
	•	Poder informar qual endereço é o principal da pessoa  
```
	Uma Pessoa deve ter os seguintes campos:  
		•	Nome
		•	Data de nascimento
		•	Endereço:
			•	Logradouro
			•	CEP
			•	Número
			•	Cidade

Requisitos  
	•	Todas as respostas da API devem ser JSON  
	•	Banco de dados H2

## 🛠️  TECNOLOGIAS UTILIZADAS

Nessa solução foram utilizadas as seguintes tecnologias:
`#0969DA`
* Java 17
* Spring Boot 3.0.2
* JUnit 4.13
* H2database 2.1
* Maven
* Postman para os testes de integração
`#0969DA`

## 🔧  INSTALAÇÃO

Antes da instalação se pressupõe que o java 19 e o Git já estão instalados, se não estiverem devem ser instalados antes de continuar.

### Baixando os arquivos da aplicação

Para baixar os arquivos do aplicativo deve-se usar o comando:

git clone https://github.com/raderleao/rh-pessoa-api.git


Depois entrar na pasta do aplicativo:
```
cd ger-pessoa-api
```
Os demais comandos a seguir devem ser feito a partir da pasta do aplicativo.


### Aplicação
	Para executar a aplicação recomenda-se usar uma das duas formas:

#### 1 - Diretamente pelo Maven
	Para executar a aplicação diretamente pelo maven use o comando:

 - No linux:
```
./mvnw clean spring-boot:run
```
 - No windows:
```
mvnw.cmd clean spring-boot:run
```

### 2 - Gerando um executável java (JAR)

	Para gerar um executável java use comando:

 - No linux:
```
./mvnw clean package
```

 - No windows:
```
mvnw.cmd clean package
```

Após o executável ser gerado, use o comando para inicializar o aplicativo:

```linux
java -jar target/ger-pessoa-api-0.0.1-SNAPSHOT.jar
```

O aplicativo se inicializará no endereço:
```url
http://127.0.0.1:8080
```
# API REST - SPRING BOOT

### Gerenciando o Registro de Pessoas e respectivos endereços</h3>

##### • Adicionando uma Pessoa

Para adicionar uma pessoa, precisamos informar os campos `nome` e `dataNascimento`. O exemplo a seguir mostra o código em formato `JSON` para envio da requisiçao.

URI para adição da pessoa.

`POST`- localhost:8080/pessoas

```json
{
    "nome": "Rader Leao",
    "dataNascimento": "1985-07-27"
}
```
após o envio da requisição obteremos o código de status de respostas http - ` STATUS 201 CREATED`.


####  • Removendo uma Pessoa

Para removermos uma pessoa, precisamos somente informar o `ID` de uma pessoa como parametro apos o endereço da `URI`. Conforme exemplo a seguir:

Neste exemplo estamos passando o `ID` 1 e informamos o método de requiçao HTTP `DEL`

`DEL`- localhost:8080/pessoas/1

existindo o `ID` cadastrado, obteremos o código de status de resposta http - `STATUS 204 - NO CONTENT`.


#### • Listando as Pessoas cadastradas
Para listamos as pessoas cadastradas, enviaremos a seguinte requisiçao:

`DEL`- localhost:8080/pessoas

caso tenha pessoas cadastradas retornara com o status http - `200 OK`.

Body de retorno da requisiçao:

```JSON

[
    {
        "id": 1,
        "nome": "Rader Bruno Leao",
        "dataNascimento": "2007-07-07"
    },
    {
        "id": 2,
        "nome": "Bruna Leao",
        "dataNascimento": "2007-07-07"
    },
    {
        "id": 3,
        "nome": "Rader Leao",
        "dataNascimento": "2007-07-07"
    }
]
```

#### • Buscando Pessoas cadastradas
Para buscarmos uma pessoa, precisamos somente informar o `ID` de uma pessoa como parametro apos o endereço da `URI`. Conforme exemplo a seguir:

Neste exemplo estamos passando o `ID` 1 e informamos o método de requiçao HTTP `GET`

`GET`- localhost:8080/pessoas/1


Body de retorno da requisiçao:
```JSON
[
    {
        "id": 1,
        "nome": "Rader Bruno Leao",
        "dataNascimento": "2007-07-07"
    }
]
```
existindo o `ID` cadastrado, obteremos o código de status de resposta http - `STATUS 200 OK`.

#### Da mesma forma funcionara para cadastro de `Estado` localhost:8080/estados/ e `Cidade` localhost:8080/cidades/


### Gerenciando o Registro de Endereços

##### • Adicionando um endereço a uma pessoa

Para adicionar um endereço a uma pessoa, precisamos informar os campos `cep`, `logradouro`, `numero`, `complemento`, `bairro` e `cidade`. 
O exemplo a seguir mostra o código em formato `JSON` para envio da requisiçao com mé
Para adicionar um endereço a uma pessoa, precisamos informar os campos `cep`, `logradouro`, `numero`, `complemento`, `bairro` e `cidade`. O exemplo a seguir mostra o código em formato `JSON` para envio da requisiçao.todo POST.

URI para adição da pessoa.

`POST`- localhost:8080/pessoas/2/enderecos

onde o número `2` no endereço acima, representa o código `ID`da pessoa cadastrada na qual será vinculado o endereço.
Caso não haja ainda endereço cadastrado, o sistema definirá o endereço automaticamente como principal tendo a possibilidade de uma pessoa, ter vários endereços.

 • Código de exemplo de envio de JSON para cadastro de endereço vinculado a uma pessoa:
 ```JSON
{
        "cep": "87.543-000",
        "cidade": {
            "id": 2
        },
        "logradouro": "Rua Sao Pantaleao ",
        "numero": "560",
        "complemento": "Quadra D",
        "bairro": "Parque Shalon"
    }
```
após o envio da requisição obteremos o código de status de respostas http - ` STATUS 201 CREATED`.

as funcionalidades de Listagem, remoçao e atualização, funcionam da mesma forma aos exemplos anteriores, conforme a seguir:


 • Lista os endereços da pessoa
```
`GET`- localhost:8080/pessoas/1/enderecos
```

 • Atualiza o endereço da pessoa
```
`PUT`- localhost:8080/pessoas/1/enderecos
```

 • Remove o endereço da pessoa
```
`DEL`- localhost:8080/pessoas/1/enderecos
```


##### • Atualizando um endereço como principal

Caso o consumidor da API decida alterar o cadastro do endereço principal da pessoa, ela poderá requisitar através da URI:

```
`PUT`- localhost:8080/pessoas/1/enderecos/3/principal
```
Método de requiçao HTTP `PUT`informando o `ID`da pessoa e o `Código ID do Endereço` cadastrado para aquela pessoa


## ⚙️ Executando os testes

Realizaremos os testes através da ferramento do JUNIT conforme código abaixo:

```JAVA

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
```


## 📌 Versão

Nós usamos [GitHub](http://github.org/) para controle de versão. Para as versões disponíveis, observe as [tags neste repositório](https://github.com/raderleao). 

## ✒️ Autores

* **Ráder Leão** - ** - Dev SpringBoot / Java - [https://github.com/raderleao]

## 📄 Licença

Este projeto está sob a licença (sua licença) - veja o arquivo [LICENSE.md](https://github.com/raderleao/projeto/licenca) para detalhes.
