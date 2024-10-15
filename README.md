# Escala - Aplicativo de Escalas

## Descrição

O projeto **Escala** é um aplicativo desenvolvido para gerenciar escalas de trabalho. Ele foi construído utilizando **Java 21** e **Spring Boot 3.3.x**, juntamente com **Hibernate** para a gestão de persistência de dados em um banco de dados **MySQL**. O aplicativo possui três entidades principais: **Pessoa**, **Equipe** e **Escala**.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.3.x**
- **Hibernate**
- **MySQL**
- **Maven** (ou Gradle, dependendo da sua configuração)

## Estrutura do Projeto

### Entidades

1. **Pessoa**
   - Representa um indivíduo que pode ser escalado.
   - Atributos: `id`, `cpf`, `nome`, `email`, `equipe`, `senha`.

2. **Equipe**
   - Representa um grupo de pessoas que trabalham juntas.
   - Atributos: `id`, `nome`, `membros`, `escalas`.

3. **Escala**
   - Representa uma escala de trabalho atribuída a uma equipe ou pessoa.
   - Atributos: `id`, `titulo`, `data`, `pessoasExtras`, `equipe`, `descricao`.

## Configuração do Banco de Dados

1. Certifique-se de ter o MySQL instalado e em execução.
2. Crie um banco de dados chamado `escala_db` (ou outro nome de sua preferência) com o seguinte comando SQL:

    ```sql
    CREATE DATABASE escala_db;
    ```

3. Configure as credenciais do banco de dados no arquivo `application.properties` (ou `application.yml`) da seguinte forma:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/escala_db
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

## Execução do Projeto

Para executar o projeto, siga os seguintes passos:

1. Clone o repositório:

    ```bash
    git clone https://github.com/gustavomf1/Escalas-backend
    ```

2. Navegue até o diretório do projeto:

    ```bash
    cd escala
    ```

3. Execute o projeto com Maven:

   - Com Maven:
    ```bash
    ./mvnw spring-boot:run
    ```

## Endpoints

O aplicativo oferece os seguintes endpoints para interagir com as entidades:

- **Pessoa**
  - `GET /api/pessoas` - Listar todas as pessoas
  - `POST /api/pessoas` - Criar uma nova pessoa
  - `PUT /api/pessoas/{id}` - Atualizar uma pessoa
  - `DELETE /api/pessoas/{id}` - Deletar uma pessoa

- **Equipe**
  - `GET /api/equipes` - Listar todas as equipes
  - `POST /api/equipes` - Criar uma nova equipe
  - `PUT /api/equipes/{id}` - Atualizar uma equipe
  - `DELETE /api/equipes/{id}` - Deletar uma equipe

- **Escala**
  - `GET /api/escalas` - Listar todas as escalas
  - `POST /api/escalas` - Criar uma nova escala
  - `PUT /api/escalas/{id}` - Atualizar uma escala
  - `DELETE /api/escalas/{id}` - Deletar uma escala
