# Auth Service

> Esta API é um sistema de autenticação e autorização independente e desacoplado. Funcionando de forma autônoma e gerando tokens para as outras API consumirem.


## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

- `Java 24`
- `Spring Boot 3.5.4`
- `PostgresSQL 17`
- Ler esta `documentação.

## 🚀 Instalar

Para instalar o Auth Service, siga estas etapas:

Linux:

```
./mvnw clean install
```

Windows:

```
mvn clean install
```

## ☕ Rodar

Para usar o Auth Service, siga estas etapas:

Linux:
```
./mvnw spring-boot:run
```

Windows:
```
mvn spring-boot:run
```

## 📚 Endpoints Principais

`POST` - /auth/login

`No Role`

Payload:
```
{
    "username": "",
    "password": ""
}
```

`POST` - /auth/register

`No Role`

Payload:
```
{
    "name": "",
    "email": "",
    "password": ""
}
```

`GET` - /auth/users

`CLIENT ROLE`

Essa rota busca os usuários de forma paginada de acordo com os parametros passados na url. 
Sendo eles:
- `search`: que busca por nomes e emails;
- `role`: que traz os usuários com a role solicitada.
- `page`: a pagina que você quer buscar;
- `size`: quantos usuários por página.

Se nenhum parâmetro for passado, a api buscará por todos os usuários.


```
/auth/users?search=busca&role=role&page=1&size=10
```

`DELETE` - /auth/users/delete

`ADMIN ROLE`

```
/auth/users/delete/{id-do-usuario-a-ser-deletado}
```

`PUT` - /auth/users/update/role

Payload:
```
{
    "id": "",
    "role": ""
}
```

`PUT` - /auth/users/update

Payload:
```
{
    "id": "",
    "name": "",
    "email": "",
    "password": "",
}
```
Nesta rota, não se faz necessário enviar todos os parâmetros. Deve-se enviar apenas os campos a serem alterados.

## 🔐 Autenticação

Este serviço utiliza autenticação via JWT.

Após realizar login, você deve usar o token retornado no header das requisições:

```
Authorization: Bearer <seu-token>
```

O acesso ao ecossistema de api's é role-based.

## ⚙️ Variáveis de Ambiente

Crie um arquivo `.env` com as seguintes variáveis:

```env
DB_URL=jdbc:postgresql://localhost:5432/auth_db
DB_USERNAME=postgres
DB_PASSWORD=yourpassword
JWT_SECRET=my-very-secret-key
```

# 📦 Integração com Outros Projetos

Clone este repositório, alterando seu nome para o escopo desejado.

Exemplo: `fromhel-auth-service`.

Faça as alterações necessárias para o cliente.

Faça testes, mantenha o padrão de segurança e qualidade.

Seja feliz :)