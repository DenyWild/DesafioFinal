# DesafioFinal

Este é um repositório no qual me propus a criar um Desafio do zero
Apenas com ideias e pondo-as em prática

Para utilizar de maneira correta é preciso que você tenha o PostgresSQL instaalado por este Link: https://www.postgresql.org/download/
ou indo diretamente na fonte no seguinte link: https://www.enterprisedb.com/downloads/postgres-postgresql-downloads

Ao instalar você pode configurar o seu username e password da maneira que se sentir melhor, lembrando que dependendo de como você configurar o seu Banco de dados você terá que modificar o application.properties da sua aplicação.

Link com imagens de exemplo pra instalação: https://www.postgresqltutorial.com/postgresql-getting-started/install-postgresql/
Link com guia para baixar e instalar postgreSQL no windows 11: https://youtu.be/IYHx0ovvxPs?feature=shared

## Contém o Spring Security e o OpenAPI para testar os endpoints de maneira veloz:

![swagger](https://github.com/DenyWild/DesafioFinal/assets/78888162/058229eb-5244-4f1f-8ff2-8bcfef0b0e46)

E para testar com acesso total você pode ir no swagger pelo **localhost:(serverPort)/swagger-ui.html**
Após isso você irá utilizar o endpoint **SignIn** com as seguintes informações:

![admin](https://github.com/DenyWild/DesafioFinal/assets/78888162/75f9a5d9-071e-4438-b067-a9d892804e1e)


Com isso ele irá retornar um token de acesso autenticado como vemos na imagem seguinte:

![token](https://github.com/DenyWild/DesafioFinal/assets/78888162/e186e3b3-7102-4587-93fc-9d445458368e)


Então você irá ao authorize ->

![authorize](https://github.com/DenyWild/DesafioFinal/assets/78888162/45721018-f199-4154-8aca-014239cd5639)


Clique nele e ira abrir uma aba para logar, nesta aba você irá colocar o token que recebeu quando utilizou o endpoint **SigIn***

![image](https://github.com/DenyWild/DesafioFinal/assets/78888162/fc53fb42-b592-481f-81fd-030039d3a293)


e com isso você estará autenticado e autorizado para utilizar os demais endpoints.







