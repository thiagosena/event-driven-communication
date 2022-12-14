## 💻 Project

Spring Cloud Stream is a framework that is part of the Spring Cloud ecosystem. It is possible to develop
event-driven applications and Stream applications, which communicate in real time and asynchronously using messaging.

It abstracts the use of a message broker, making it possible to port our source code and all business rules to a
different message broker, without having to change it.

## ✨ Technologies

- [Spring boot](https://spring.io/projects/spring-boot): Makes it easy to create stand-alone, production-grade Spring
  based Applications that you can "just run".
- [RabbitMQ](https://www.rabbitmq.com): is an open-source message-broker software that originally implemented the
  Advanced Message Queuing Protocol and has since been extended with a plug-in architecture to support Streaming Text
  Oriented Messaging Protocol, MQ Telemetry Transport, and other protocols.
- [Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream): Spring Cloud Stream is a framework for building
  highly scalable event-driven microservices connected with shared messaging systems.
- [MySQL](https://www.mysql.com): is an open-source relational database management system (RDBMS).

## 🚀 How to execute

```shell
# Runing message broker and mysql database
docker-compose up -d

# Runing application
cd application
mvn spring-boot:run

# Runing schedule
cd schedule
mvn spring-boot:run

# Runing health-check
cd health-check
mvn spring-boot:run

# Runing metric
cd metric
mvn spring-boot:run
```

## ✨ How to use

### Application endpoints

- list all:

```shell
curl --location --request GET 'http://localhost:8082/v1/apps'
```

- get by id:

```shell
curl --location --request GET 'http://localhost:8082/v1/apps/:appid'
```

- create app:

```shell
curl --location --request POST 'http://localhost:8082/v1/apps' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Atlasian",
    "address": "https://www.atlassian.com/healthcheck"
}'
```

- update app:

```shell
curl --location --request PUT 'http://localhost:8082/v1/apps/47e1dcdf-3421-46eb-873a-08b4689ba46a' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Atlassian",
    "address": "https://www.atlassian.com/healthcheck"
}'
```

### Schedule endpoints

- list all:

```shell
curl --location --request GET 'http://localhost:8083/v1/schedules'
```

- list by id:

```shell
curl --location --request GET 'http://localhost:8083/v1/schedules/:id'
```

- create:

```shell
curl --location --request POST 'http://localhost:8083/v1/schedules' \
--header 'Content-Type: application/json' \
--data-raw '{
    "runInterval": 1,
    "appId": "47e1dcdf-3421-46eb-873a-08b4689ba46a",
    "checkConfig": {
        "timeout": 30,
        "type": "HTTP"
    }
}'
```

- update:

```shell
curl --location --request PUT 'http://localhost:8083/v1/schedules/6a086d93-2c7e-49ad-908b-c25d990de160' \
--header 'Content-Type: application/json' \
--data-raw '{
    "runInterval": 1,
    "appId": "47e1dcdf-3421-46eb-873a-08b4689ba46a",
    "checkConfig": {
        "timeout": 30,
        "type": "PING"
    }
}'
```

- delete:

```shell
curl --location --request DELETE 'http://localhost:8083/v1/schedules/6a086d93-2c7e-49ad-908b-c25d990de160'
```

### Metric endpoints

- list metrics:

```shell
curl --location --request GET 'http://localhost:8085/v1/metrics'
```

- list events:

```shell
curl --location --request GET 'http://localhost:8085/v1/metrics/events'
```