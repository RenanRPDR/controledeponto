  # Sobre o projeto
  Essa API controla as horas dos funcionáriosa através de batidas de ponto, realiza validações e o registro das mesmas.

  ##  Tecnologias
  Foram utilizadas as seguintes tecnologias no projeto:

  - [Java 11](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)
  - [Maven 3.6.3](AARUMAAAAAAAAR)
  - [Spring Boot 2.6.6](https://spring.io/projects/spring-boot)
  - [H2 Databse](https://www.h2database.com/html/main.html)
  - [DevTools](https://www.baeldung.com/spring-boot-devtools)
  - [SpringToolSuite4](https://spring.io/tools)
  - [Github](https://github.com/)
   ---

  ## Como baixar e instalar projeto
  ## Pré-requisitos

  ### Instale o SpringToolSuite4 ou uma IDE de sua preferência:
  - Clique [aqui](https://spring.io/tools) para fazer o download do SpringToolSuite4, lembre-se de escolher o instalador para o sistema operacional que você usa.

  ### Baixando o projeto do github
  - Use o link https://github.com/RenanRPDR/controledeponto.git para clonar o repositório ou baixar ele zipado.
  - Para baixar o projeto zipado, basta acessar o link acima no seu navegador, clicar no botão CODE e na opção Download ZIP.
  - Para clonar com github, utilize o comando abaixo dentro do diretório onde você deseja manter o projeto
      ```bash
      git clone https://github.com/RenanRPDR/controledeponto.git
      ```


  ### Subindo o projeto
  - Abra a classe ControledepontoApplication no package br.com.controledeponto
  - Selecione o nome da classe ControledepontoApplication
  - Clique com o botão direito
  - Selecione Run As
  - Depois selecione Spring Boot App

  ### Acessando o banco em memória
  - Após levantar sua aplicação o H2 Database estará disponível na URL: http://localhost:8080/h2-console
  - As configurações do banco podem ser consultadas no arquivo "application.properties"
  - Caminho do arquivo: src/main/resources/application.properties
