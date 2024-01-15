
# Magoya Challenge Service

This service, provides functionality around Accounts and Transactions.


## Tech Stack

**API:**
Java 17 with Springboot 3.2.1

**Dependencies manager**: Maven

**Libraries:**
- Spring DataJPA,
- Spring DataMongoDB,
- Lombok
- Spring Web
- Spring AMQP
- Spring Validation

**Databases:**
- H2
- MongoDB


## Installation


## Run Locally
### Requirements
Before starting you need installed in your system
- JDK 17
- Maven
- Git
- Running a MongoDB instance with default config. Docker recommended.
- Running RabbitMQ instance with default config. Docker recommended.

### Starting locally
Clone project

```bash
  git clone https://github.com/pccopa/magoya
```
Go to the project directory

```bash
  cd magoya
```

Installing dependencies
```bash
  mvn install
```

Starting project

```bash
  mvn spring-boot:run
```
Api operation it will be available on: ```http://localhost:8080/v1/```

### Properties

Special warning when you use this app on localhost, you need to update application.properties and set up correctly your hosts, users and passwords.
- mongdb
- rabbitmq

## API Documentation

**Postman:**
You can find the [postman collection](Magoya%20Challenge.postman_collection.json) in this repo

How to use it: [Help me file](HELP.md)