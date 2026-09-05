#  Biblioteca API

API REST para gerenciamento de livros, desenvolvida com **Java e Spring Boot**.

O projeto foi desenvolvido com foco em boas práticas de desenvolvimento backend, organização em camadas, persistência de dados, validação de entradas, tratamento de exceções e documentação da API.

---

##  Tecnologias

*  Java
*  Spring Boot
*  Spring Data JPA / Hibernate
*  MySQL
*  Bean Validation
*  Swagger
*  Maven

---

##  Funcionalidades

* Cadastro de livros
* Listagem de livros
* Busca de livro por ID
* Busca de livros por título
* Busca de livros por autor
* Atualização de livros
* Exclusão de livros
* Validação dos dados recebidos
* Validação de ISBN duplicado
* Tratamento global de exceções
* Documentação da API com Swagger

---

##  Arquitetura

O projeto utiliza uma organização em camadas para separar as responsabilidades da aplicação:

```text
src/main/java/com/biblioteca
│
├── controller
│   └── LivroController
│
├── service
│   └── LivroService
│
├── repository
│   └── LivroRepository
│
├── model
│   └── LivroModel
│
├── dto
│   └── LivroRequestDTO
│
└── exception
    ├── GlobalExceptionHandler
    ├── LivroNaoEncontradoException
    └── IsbnDuplicadoException
```

### Responsabilidades

* **Controller:** recebe as requisições HTTP e direciona as operações.
* **Service:** concentra as regras de negócio da aplicação.
* **Repository:** realiza a comunicação com o banco de dados.
* **Model:** representa a entidade persistida no banco.
* **DTO:** define os dados recebidos nas requisições.
* **Exception:** centraliza o tratamento das exceções da API.

---

##  Endpoints

| Método   | Endpoint                                | Descrição                |
| -------- | --------------------------------------- | ------------------------ |
| `GET`    | `/livros`                               | Lista todos os livros    |
| `GET`    | `/livros/buscar/id?id={id}`             | Busca um livro pelo ID   |
| `GET`    | `/livros/buscar/titulo?titulo={titulo}` | Busca livros pelo título |
| `GET`    | `/livros/buscar/autor?autor={autor}`    | Busca livros pelo autor  |
| `POST`   | `/livros`                               | Cadastra um novo livro   |
| `PUT`    | `/livros/{id}`                          | Atualiza um livro        |
| `DELETE` | `/livros/{id}`                          | Exclui um livro          |

---

##  Exemplo de cadastro

### `POST /livros`

```json
{
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "isbn": "9780132350884",
  "anoPublicacao": 2008,
  "categoria": "Programação",
  "quantidade": 3
}
```

### Exemplo de resposta

```json
{
  "id": 1,
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "isbn": "9780132350884",
  "anoPublicacao": 2008,
  "categoria": "Programação",
  "quantidade": 3
}
```

---

##  Validações

A API possui validações para os dados recebidos nas requisições.

Entre elas:

* Título obrigatório
* Autor obrigatório
* ISBN obrigatório
* Categoria obrigatória
* Ano de publicação obrigatório
* Quantidade obrigatória
* Quantidade não pode ser negativa
* Limite de caracteres para campos de texto
* ISBN não pode ser duplicado

Quando ocorre um erro de validação, a API retorna uma resposta HTTP `400 Bad Request` informando os campos que precisam ser corrigidos.

---

##  Tratamento de exceções

A aplicação possui tratamento global de exceções utilizando `@RestControllerAdvice`.

Exemplos:

| Situação             |              HTTP |
| -------------------- | ----------------: |
| Livro não encontrado |   `404 Not Found` |
| ISBN já cadastrado   |    `409 Conflict` |
| Dados inválidos      | `400 Bad Request` |

---

##  Banco de dados

O projeto utiliza **MySQL** para persistência dos dados.

Crie o banco:

```sql
CREATE DATABASE biblioteca;
```

Depois, configure as credenciais do banco no arquivo `application.properties`.

Exemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

A variável de ambiente `DB_PASSWORD` deve conter a senha do seu banco de dados.

> O projeto não deve armazenar senhas ou outras credenciais diretamente no código-fonte.

---

##  Documentação da API

A API possui documentação interativa utilizando **Swagger / OpenAPI**.

Com a aplicação em execução, acesse:

```text
http://localhost:8080/swagger-ui/index.html
```

Por meio do Swagger é possível visualizar e testar os endpoints da API.

---

##  Como executar o projeto

### 1. Clone o repositório

```bash
git clone https://github.com/vtoorzDev/biblioteca-api.git
```

### 2. Entre no diretório

```bash
cd biblioteca-api
```

### 3. Configure o banco de dados

Crie o banco MySQL:

```sql
CREATE DATABASE biblioteca;
```

Configure a variável de ambiente:

```text
DB_PASSWORD=sua_senha
```

### 4. Execute a aplicação

No Windows:

```bash
mvnw.cmd spring-boot:run
```

Ou, caso o Maven esteja instalado:

```bash
mvn spring-boot:run
```

A aplicação será iniciada em:

```text
http://localhost:8080
```

---

##  Testes

O projeto possui testes básicos para verificar o comportamento das principais funcionalidades da aplicação.

---

##  Objetivo do projeto

Este projeto foi desenvolvido como parte da minha evolução prática em **desenvolvimento backend com Java e Spring Boot**, aplicando conceitos como:

* Desenvolvimento de APIs REST
* Injeção de dependências
* Arquitetura em camadas
* Spring Data JPA
* Persistência com MySQL
* Validação de dados
* Tratamento de exceções
* Documentação de APIs
* Organização e boas práticas de código

---
