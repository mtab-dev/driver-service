# Auth Service

> Esta API é um sistema de autenticação e autorização independente e desacoplado. Funcionando de forma autônoma e gerando tokens para as outras API consumirem.

### Ajustes e melhorias

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas para as seguintes tarefas:

- [ ] Update de usuários
- [ ] Deletar usuários

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
mvnw.cmd clean install
```

## ☕ Rodar

Para usar o Auth Service, siga estas etapas:

Linux:
```
./mvnw spring-boot:run
```

Windows:
```
mvnw.cmd spring-boot:run
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

Essa rota busca os usuários de acordo com os parametros passados na url. Sendo eles e-mail e roles. Se nenhum parâmetro for passado, a api buscará por todos os usuários.

```
/auth/users?email=emailb@email.com&role=role
```

`DELETE` - /auth/delete

`ADMIN ROLE`

```
/auth/users/delete/{id-do-usuario-a-ser-deletado}
```

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