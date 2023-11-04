# Test task - Games Platform API

## Task

Implement simplified version of https://lostgame.tech/docs/communications-protocol/api-for-processing-gameoperations/

## Running locally
Requirements:
- Java 17 or newer
- MySQL 8.0.35 or newer

Tested on setup:
- Java 17
- MySQL 8.0.35
- OS: Windows

### MySQL

Application requires MySQL database running on `localhost` port `3306`
and `TestTask` scheme/database and will try to use `root` as username and `password` as password to authenticate.

Provided constants can be overridden by specifying next environment variables:
- MYSQL_HOST
- MYSQL_PORT
- MYSQL_SCHEMA
- MYSQL_USERNAME
- MYSQL_PASSWORD

Application expects tables be already create at point of start up.

Scripts to create Schema and required tables can be found in [init script](./infrastucture/my-sql/my-sql-init.sql).  
Same folder also contains [test data population script](./infrastucture/my-sql/exmple-data.sql)

### Running application

To run application locally from command line - execute `./gradlew bootRun` from project root folder

### Authorization

Application uses signing as requested by task.

Default secret phrase is `default-secret`, but can be customized via `SECRET_STRING` environment variable

### Example `curl` requests

Application contains one endpoint that serves three types of requests.

Since application requires signing these curl requests are written with assumption that default secret phrase is used.

If default secret phrase has been changed, `Sign` header must be adjusted appropriately

Postman collection also available in [Postman collection export file](/documentation/Postman%20for%20TestTask.postman_collection.json)

`api: balance`:
```shell
curl --location --request POST 'http://localhost:8080/open-api-games/v1/games-processor' \
--header 'Sign: defb88c792119ca9dc185539c7850155' \
--header 'Content-Type: application/json' \
--data-raw '{
    "api": "balance",
    "data": {
        "currency": "USD",
        "userId": 1
    }
}'
```

`api: credit`:
```shell
curl --location --request POST 'http://localhost:8080/open-api-games/v1/games-processor' \
--header 'Sign: a9f7be07bfa014998264326fd0d1e0df' \
--header 'Content-Type: application/json' \
--data-raw '{
    "api": "credit",
    "data": {
        "currency": "USD",
        "userId": 1,
        "amount": 120
    }
}'
```

`api: debit`:
```shell
curl --location --request POST 'http://localhost:8080/open-api-games/v1/games-processor' \
--header 'Sign: 0bc7b3e0e89518f867731f8272233a8a' \
--header 'Content-Type: application/json' \
--data-raw '{
    "api": "debit",
    "data": {
        "currency": "USD",
        "userId": 1,
        "amount": 120
    }
}'
```