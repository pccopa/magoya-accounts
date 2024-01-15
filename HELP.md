##  How to use it - Accounts and Transactions.

Accounts and Transactions service, is using an hexagonal architecture, providing features to create accounts, make deposits and withdrawals.

At first moment, project starts with H2 database, that's because its well known technology, so you can see some legacy H2 code.

Later, mongodb has incorporated and was set up  as like default infrastructure database.

All ID are string based, so H2, mongo or any database can be implemented just integrating their adapter.

## Accounts

### Create an account
You can create an account providing owner's name, and alias. To make it, you need to POST to this endpoint.

- http://localhost:8080/v1/accounts

#### Request Body
Its required to work correctly a body like this.
```json
{
  "ownerName":"pepe",
  "aliasAccount":"jugo.manzana.naranja"
}
```
- aliasAccount: has a validator to avoid duplicity of alias over accounts.

#### Example
An example curl will be provided you to test the service, regardless you access to postman collection provided on the root of this project.

```curl
curl --location 'localhost:8080/v1/accounts' \
--header 'Content-Type: application/json' \
--data '{
    "ownerName":"pepe",
    "aliasAccount":"jugo.manzana.naranja"
}'
```
#### Response
If everything was correct, service returns you a response like this. With this response an event will be triggered and published on RabbitMQ.

```json
{
  "id": "65a58bd1989f14695fd4665f",
  "owner": "pepe",
  "balance": 0,
  "accountNumber": "jugo.manzana.naranja"
}
```

### Getting balance
After you have an account created, you are ready to check your balance account. You can do it on this endpoint.

- http://localhost:8080/v1/accounts/{accountID}

#### Example
With the same ID generated at create operation 65a58bd1989f14695fd4665f, we can make a get balance request.
```curl
curl --location 'localhost:8080/v1/accounts/65a58bd1989f14695fd4665f/balance'
```
#### Response
If provided account id its correct, then you can see a response like this.


```json
{
    "accountId": "65a58bd1989f14695fd4665f",
    "balance": 15400.0
}
```
## Transactions

Once you have an account created, you can start to make simple transactions.

- DEPOSIT: Is a no restriction operation. Their purpose its to increment available amount of account or balance.
- WITHDRAWAL: Is a restricted operation. Only can be completed when available balance is equals or greater than extraction amount.

Every transaction registered on this application, will be trigger an event to publish on RabbitMQ.
When your DEPOSIT transaction exceeds USD 10.000, will be treated like an special event too.

To use it, you have this endpoint with POST operation.

- http://localhost:8080/v1/transactions


#### Request Body
Its required to work correctly a body like this.
```json
{
  "accountId":"65a58bd1989f14695fd4665f",
  "amount":15000.0,
  "txType": "DEPOSIT"
}
```

#### Example
An example curl will be provided you to test the service, regardless you access to postman collection provided on the root of this project.

```curl
curl --location 'localhost:8080/v1/transactions' \
--header 'Content-Type: application/json' \
--data '{
    "accountId":"65a58bd1989f14695fd4665f",
    "amount":15000.0,
    "txType": "DEPOSIT"
}'
```
- Remember: only DEPOSIT or WITHDRAWAL operation are allowed.

#### Response
If everything was correct, you can see a response similar to this one 

```json
{
  "accountId": "65a58bd1989f14695fd4665f",
  "balance": 15370.0
}
```

On the case you are doing an withdrawal operation, an exceed your balance, an exception will be occurred.

## Extra information.

At this moment, there are two different ways to work with. MongoDB is active as default database, but H2 is available and is already configured.
To achieve this goal, only removes @Primary annotation of AccountPersistenceMongoAdapter on infrastructure layer.
At the same time, adds @Primary annotation to AccountPersistenceJpaAdapter on infrastructure layer too.


The purpose of hexagonal architecture is to provide a scalable and maintainable solution, although I consider it over-engineering for the challenge presented. The idea was to propose a clear working approach for when dealing with a real project.

### Further releases

- Maybe should be considered the use of Mapstruct to avoid manual mapper
- Must be unit and integration test, using cucumber and junit.
- App need a Controller Advice to handle multiple exception scenarios. Today no exceptions are handled.
- Consider every single property like a Value-Object
- Incorporate a few extra layers of abstraction to gain better decouple
