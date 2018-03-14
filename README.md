# Introdução
Essa é uma aplicação simples de gestão de usuários.

# Descrição
Existem dois tipos de usuários no sistema: administrador que possui permissão de leitura/escrita e usuário comum que possui permissão somente leitura. <br/>
Somente um usuário do tipo administrador pode cadastrar novos usuários na base. Para isso já há previamente cadastrado na base um usuário do tipo administrador. <br/>
Há validações nos atributos do usuário de modo a não permitir entrada de CPF e Documento inválidos ou repetidos.

# Docker
Para executar a aplicação em container docker, basta seguir os passos a seguir: <br/>
1. Escolha o nome da imagem e execute o comando a seguir:
`docker build -t NOME_DA_IMAGEM .`
2. Crie uma instância de container para a aplicação informando a porta da sua máquina que você deseja liberar para essa instância e o nome da imagem:
`docker -p PORTA:8080 -d NOME_DA_IMAGEM`

# Executando

Para logar no sistema basta executar um POST com as seguintes credenciais: <br/>
```json
{
    "Document": "76583352311",
    "Password": "123456"
}
```

O servidor irá retornar o token de autenticação no cabeçalho da resposta.<br/>
`Ex.: Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI3NjU4MzM1MjMxMSIsImV4cCI6MTUyMDk4ODE0MH0.G0Z30qJLEfwBQjW2-I3dsrN5JprmD7e5HeOCxPgRX9DQJew9qkOcFw1L3VGOaBv0RxB6f-McIpD7jd31mQC_jQ`

Agora, basta usá-lo no cabeçalho da requisição.

# Documentação de API
A documentação da API se encontra no swagger.<br/>

Ex.: Rodando aplicação na localmente na porta 8085:
http://localhost:8085/swagger-ui.html

