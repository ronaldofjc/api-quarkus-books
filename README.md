## API Books

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Pre-Requires

- JDK 11
- Maven 3
- Docker ([Install](https://docs.docker.com/v17.09/engine/installation/linux/docker-ce/ubuntu/ "Install") | [Configure](https://docs.docker.com/v17.09/engine/installation/linux/linux-postinstall/ "Configure"))

## Pre-Requires Local

- MongoDB
```shell script  
  docker pull mongo
  docker run --name mongodb -p 27017:27017 -d mongo
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
  mvn compile quarkus:dev
```

## Packaging and running the application

The application can be packaged using:
```shell script
  mvn package
```
It produces the `books-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
  mvn package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/books-1.0.0-SNAPSHOT-runner.jar`.

## Swagger
- The generated swagger html page is available in the following address
    `http://localhost:8080/swagger-ui`

## Terraform

- Terraform ([Install](https://learn.hashicorp.com/tutorials/terraform/install-cli "Install"))

- Initialize directory
```shell script
  cd terraform/
  terraform init
```

- Execution plan
```shell script
  terraform plan
```

- Apply configuration
```shell script
  terraform apply
```

- Destroy infraestructure
```shell script
  terraform destroy
```
