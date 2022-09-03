# $name$

An example application built from the [ce3.g8 template](https://github.com/typelevel/ce3.g8).

## Run application

```bash
sbt run
```

$if(dockerize.truthy)$
## Dockerize

```bash
# should create `./target/docker/stage/Dockerfile`
sbt "Docker/publishLocal"

# Example Docker run command (make sure to forward to exposed port)
docker run -p 8080:8080 cats-effect-3-app:0.1.0-SNAPSHOT
```
$endif$


$if(is-server.truthy)$
## Server
Testing the (GET) _/hello/\${name}_ endpoint
```bash
curl localhost:8080/hello/bob
# "Hello bob"
```

Testing the (POST) _/sort_ endpoint
```bash
curl --request POST 'localhost:8080/sort' \
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
# {"nums":[1,2,4,5,7]}
```
$endif$
