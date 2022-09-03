# $name$

An example application built from the [ce3.g8 template](https://github.com/typelevel/ce3.g8).

## Run application

```bash
sbt run
```

$if(dockerize.truthy)$
## Dockerize

```bash
sbt docker:publishLocal
# should create `./target/docker/stage/Dockerfile`
```
$endif$


$if(is-server.truthy)$
## Server
Testing the _POST_ endpoint!
```
curl --location --request POST 'localhost:8080/sort' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nums": [
        1,
        4,
        7,
        2,
        5
    ]
}'
```
$endif$
