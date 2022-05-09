# Aplicação Vote-Service

## Objetivo

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. A partir disso, você precisa criar uma solução back-end para gerenciar essas sessões de votação. Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST:
- Cadastrar uma nova pauta;
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default);
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta);
- Contabilizar os votos e dar o resultado da votação na pauta.

Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces pode ser considerada como autorizada. A escolha da linguagem, frameworks e bibliotecas é livre (desde que não infrinja direitos de uso).

É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.

## Ambiente para execução

Para execução do projeto basta possuir o Docker instalado.
Baixa o projeto deste repositório e com o terminal aberto na raiz da pasta do projeto execute os comandos abaixo:

Obs: Execute os 4 comandos em sequência e aguarde finalizar um comando para executar o outro.

- sudo docker-compose build
- docker-compose up
- docker-compose down
- docker-compose up

Após isso, acesse a documentação do projeto por meio do Swagger, no link: http://localhost:8087/swagger-ui.html



## Tarefas bônus

### Tarefa Bônus 1 - Integração com sistemas externos

Foi feita a integração com o serviço https://user-info.herokuapp.com/users/{cpf} utilizando o Spring Cloud OpenFeign. Sendo assim, o associado só vota se seu CPF for válido e se o serviço retornar status como ABLE_TO_VOTE.

### Tarefa Bônus 2 - Mensageria e filas

Essa implementação foi feita utilizando o Apache Kafka. Assim que uma sessão de votação acaba, o resultado é enviado para um tópico onde poderá ser consumido por outras aplicações.

Você pode visualizar as messagens do tópico através do kafdrop no endereço: http://localhost:19000/ 

### Tarefa Bônus 4 - Versionamento da API

O versionamento foi feito através de endpoints, onde para uma nova versão cria um novo recurso informando a versão da seguinte forma: 

Versão 1: /api/v1/topic

Versão 2: /api/v2/topic

## Tecnologias

O projeto foi desenvolvido com tecnologias modernas e muito utilizadas mo mercado.

Para facilitar a tratamento de Exceções foi utilizado o ExceptionHandler do Spring.

O código foi implementado com a arquitetura MVC, onde permite uma melhor organização, houve preocupação com utilização de código limpo e Design Patterns para uma melhor leitura do código.

Este sistema é uma API RestFull que foi desenvolvida com as seguintes tecnologias:


- Java 11 com auxílio de projetos do ecossistema Spring(Spring Boot, Spring Data JPA, Spring MVC, Spring Cloud OpenFeign);


- Maven para depedência;


- Junit com Mockito para implementar testes unitários;


- MySQL como banco de dados;


- Flyway para migração do banco de dados, para criação das tabelas e histórico de alterações no Scripts de banco;


- O MVC como arquitetura de software;


- Lombok que é uma biblioteca Java focada em produtividade e redução de código boilerplate;


- Apache Kafka como mensageria;


- Swagger para documentação da API.