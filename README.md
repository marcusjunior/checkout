# Checkout API

A Checkout API é uma aplicação em Spring Boot que permite criar e listar pedidos. Ela utiliza RabbitMQ para comunicação assíncrona e SQLite como banco de dados em memória.

Ao realizar um pedido, o mesmo é enviado para o componente payment-gateway via mensagem, onde mesmo valida o pagamento e devolve o resultado para a API de checkout, 
onde a mesma confirma ou cancela o pedido.

## Pré-requisitos

- JDK 17 
- Gradle 
- Docker (para RabbitMQ)
- SQlite
- SpringBoot

## Configuração

### Configurar RabbitMQ

1. Certifique-se de que o Docker está instalado e em execução.
2. Inicie um contêiner RabbitMQ:

   ```sh
   docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
   ```

## Construção e Execução

   ```sh
   ./gradlew build
   ```

   ```sh
   ./gradlew bootRun
   ```

## Swagger e documentação API

   ```sh
   http://localhost:8080/swagger-ui
   ```

