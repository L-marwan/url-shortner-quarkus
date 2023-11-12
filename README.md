# url-shortner

This is my take on the coding challenge: https://codingchallenges.fyi/challenges/challenge-url-shortener/

I wanted to take this as a chance to also get my hands on Quarkus. And I use redis for storage. 

- [x] Create endpoint to take a url and return a shortened version.
- [x] Create endpoint to redirect to an actual url give the shortened key.
- [x] Store and Retrieve the different data using the Redis client.
- [x] Create an algorithm to generate a unique key given a URL.
- [ ] Add basic authentication (maybe a limit to how many urls can be created etc..)
- [ ] Add OAuth integration ???
- [ ] Add some form of analytics endpoint
- [ ] Build a GUI


---
This project uses Quarkus.
If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

### Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

### Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

### Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/url-shortner-1.0.0-SNAPSHOT-runner`

