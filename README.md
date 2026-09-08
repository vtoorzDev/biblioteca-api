#  Biblioteca API

API REST desenvolvida com **Java e Spring Boot** para gerenciamento de uma biblioteca.

O projeto foi desenvolvido com foco em boas práticas de desenvolvimento backend, aplicando **arquitetura em camadas, injeção de dependências, persistência de dados com JPA/Hibernate, validação de entradas, tratamento global de exceções e documentação da API**.

Atualmente, a aplicação possui funcionalidades para gerenciamento de **livros e usuários**. O módulo de empréstimos será desenvolvido posteriormente.

---

##  Tecnologias

* **Java**
* **Spring Boot**
* **Spring Data JPA / Hibernate**
* **MySQL**
* **Bean Validation**
* **Swagger / OpenAPI**
* **Maven**
* **Lombok**

---

##  Funcionalidades

###  Livros

* Cadastro de livros
* Listagem de livros
* Busca de livro por ID
* Busca de livros por título
* Busca de livros por autor
* Atualização de livros
* Exclusão de livros
* Validação dos dados recebidos
* Validação de ISBN duplicado

### Usuários

* Cadastro de usuários
* Listagem de usuários
* Busca de usuário por ID
* Atualização de usuários
* Exclusão de usuários
* Validação dos dados recebidos
* Validação de e-mail duplicado

###  Recursos da API

* Arquitetura em camadas
* Injeção de dependências
* Persistência com Spring Data JPA
* Validação utilizando Bean Validation
* Tratamento global de exceções
* Respostas HTTP adequadas para cada operação
* Documentação interativa com Swagger / OpenAPI

>  **Em desenvolvimento:** módulo de empréstimos de livros.

---

##  Arquitetura

O projeto utiliza uma arquitetura em camadas, separando as responsabilidades de cada parte da aplicação.

```text
src/main/java/com/biblioteca
│
├── controller
│   ├── LivroController
│   └── UsuarioController
│
├── service
│   ├── LivroService
│   └── UsuarioService
│
├── repository
│   ├── LivroRepository
│   └── UsuarioRepository
│
├── model
│   ├── LivroModel
│   └── UsuarioModel
│
├── dto
│   ├── LivroRequestDTO
│   └── UsuarioRequestDTO
│
└── exception
    ├── GlobalExceptionHandler
    ├── LivroNaoEncontradoException
    └── IsbnDuplicadoException
```

### Responsabilidades

**Controller**

Responsável por receber as requisições HTTP, validar os dados recebidos e direcionar as operações para a camada de serviço.

**Service**

Concentra as regras de negócio e coordena as operações realizadas pela aplicação.

**Repository**

Responsável pela comunicação com o banco de dados utilizando Spring Data JPA.

**Model**

Representa as entidades persistidas no banco de dados.

**DTO**

Define os dados utilizados na comunicação entre o cliente e a API, evitando o acoplamento direto das requisições com as entidades.

**Exception**

Centraliza as exceções e o tratamento dos erros da API.

---

#  Endpoints — Livros

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

#  Endpoints — Usuários

| Método   | Endpoint         | Descrição                |
| -------- | ---------------- | ------------------------ |
| `GET`    | `/usuarios`      | Lista todos os usuários  |
| `GET`    | `/usuarios/{id}` | Busca um usuário pelo ID |
| `POST`   | `/usuarios`      | Cadastra um novo usuário |
| `PUT`    | `/usuarios/{id}` | Atualiza um usuário      |
| `DELETE` | `/usuarios/{id}` | Exclui um usuário        |

---

#  Exemplos

## Cadastro de livro

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

### Resposta

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

## Cadastro de usuário

### `POST /usuarios`

```json
{
  "nome": "Victor Araújo",
  "email": "victor@email.com",
  "telefone": "81999999999",
  "dataNascimento": "2002-05-10"
}
```

### Resposta

```json
{
  "id": 1,
  "nome": "Victor Araújo",
  "email": "victor@email.com",
  "telefone": "81999999999",
  "dataNascimento": "2002-05-10"
}
```

---

#  Validações

A API utiliza **Bean Validation** para garantir a integridade dos dados recebidos.

### Livros

Entre as validações implementadas:

* Título obrigatório
* Autor obrigatório
* ISBN obrigatório
* Categoria obrigatória
* Ano de publicação obrigatório
* Quantidade obrigatória
* Quantidade não pode ser negativa
* Limite de caracteres para campos de texto
* ISBN não pode ser duplicado

### Usuários

O módulo de usuários possui validações para garantir que os dados obrigatórios sejam informados e que as regras de negócio sejam respeitadas.

Entre elas:

* Nome obrigatório
* E-mail obrigatório
* Formato de e-mail válido
* E-mail não pode ser duplicado
* Validação dos dados recebidos pela API

Quando ocorre uma falha de validação, a API retorna `400 Bad Request` informando os dados que precisam ser corrigidos.

---

#  Tratamento de exceções

A aplicação possui tratamento global de exceções utilizando `@RestControllerAdvice`.

Exemplos de respostas:

| Situação               |              HTTP |
| ---------------------- | ----------------: |
| Dados inválidos        | `400 Bad Request` |
| Livro não encontrado   |   `404 Not Found` |
| Usuário não encontrado |   `404 Not Found` |
| ISBN já cadastrado     |    `409 Conflict` |
| E-mail já cadastrado   |    `409 Conflict` |

O tratamento centralizado evita a duplicação de código nos controllers e mantém um padrão de resposta para os erros da aplicação.

---

#  Banco de dados

A aplicação utiliza **MySQL** para persistência dos dados.


#  Documentação da API

A API possui documentação interativa utilizando **Swagger / OpenAPI**.

Com a aplicação em execução, acesse:

```text
http://localhost:8080/swagger-ui/index.html
```

O Swagger permite visualizar os endpoints disponíveis, seus parâmetros e testar as requisições diretamente pela interface.

---

#  Como executar o projeto

## 1. Clone o repositório

```bash
git clone https://github.com/vtoorzDev/biblioteca-api.git
```

## 2. Entre no diretório

```bash
cd biblioteca-api
```

## 3. Crie o banco de dados

No MySQL:

```sql
CREATE DATABASE biblioteca;
```

## 4. Configure a variável de ambiente

No Windows:

```text
DB_PASSWORD=sua_senha
```

A variável deve conter a senha do usuário do MySQL configurado na aplicação.

## 5. Execute a aplicação

Utilizando o Maven Wrapper:

```bash
mvnw.cmd spring-boot:run
```

Ou, caso o Maven esteja instalado:

```bash
mvn spring-boot:run
```

A aplicação será executada em:

```text
http://localhost:8080
```

---

#  Testes

O projeto possui testes básicos para verificar o comportamento das principais funcionalidades da aplicação.

Os testes têm como objetivo garantir o funcionamento das operações da API e reduzir possíveis regressões durante a evolução do projeto.

---

#  Próximos passos

O projeto continuará sendo evoluído com novas funcionalidades.

Entre os próximos objetivos estão:

* Implementação do módulo de empréstimos
* Relacionamento entre usuários, livros e empréstimos
* Controle da disponibilidade dos livros
* Registro de data de empréstimo e devolução
* Regras para devolução de livros
* Aprimoramento dos testes automatizados
* Evolução do tratamento de exceções
* Melhorias na documentação da API

---

#  Objetivo do projeto

Este projeto faz parte da minha evolução prática em **desenvolvimento backend com Java e Spring Boot**.

Durante seu desenvolvimento, são aplicados conceitos importantes para construção de APIs REST, como:

* Desenvolvimento de APIs REST
* Arquitetura em camadas
* Injeção de dependências
* Programação orientada a objetos
* Spring Data JPA
* Hibernate
* Persistência com MySQL
* DTOs
* Bean Validation
* Tratamento global de exceções
* Documentação com Swagger / OpenAPI
* Organização e boas práticas de código

O projeto também serve como base para a implementação de funcionalidades mais avançadas, como o **sistema de empréstimos de livros**.
