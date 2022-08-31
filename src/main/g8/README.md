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

