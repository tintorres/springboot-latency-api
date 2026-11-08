# springboot-latency-api
Springboot app to generate traffic for latency monitoring

## Maven 
* `Install` brew install maven
* `Check`   mvn --version
* `Compile`
    - Download Dependencies and compile
    - mvn clean compile
* `Package`
    - Package to jar file
    - mvn package
    - Clean and package, skip tests, verbose
    - mvn clean package -DskipTests
* `Run`
    - mvn spring-boot:run        
    - mvn spring-boot:run -Dspring-boot.run.profiles=dev        
    - mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8086"
    - mvn spring-boot:run \
        -Dspring-boot.run.arguments=" \
            --server.port=8086 \
            --server.ssl.key-alias=springboot-latency-api-sys \
            --server.ssl.key-store=classpath:keystore.sys.p12"

## Docker
* `Build` 
    - docker build -t springboot-latency-api:latest .
* `Run`
    - docker run -d \
        --name springboot-latency-api \
        -p 8084:8084 \
        springboot-latency-api:latest
* `ps`
    - docker ps -a | grep springboot-latency-api
* `stop`
    - docker stop springboot-latency-api
* `remove`
    - docker rm -f springboot-latency-api

## Prometheus
* `actuator` 
    - curl localhost:30040/actuator/prometheus | grep http_server_requests

## TLS
* `convert`
    - openssl pkcs12 -export \
        -in ../aws-devops-engineer-training/cert/lab.local/_wildcard.dev.lab.local.pem \
        -inkey ../aws-devops-engineer-training/cert/lab.local/_wildcard.dev.lab.local-key.pem \
        -out keystore.dev.p12 \
        -name springboot-latency-api-dev \
        -passout pass:changeit
    - openssl pkcs12 -export \
        -in ../aws-devops-engineer-training/cert/lab.local/_wildcard.sys.lab.local.pem \
        -inkey ../aws-devops-engineer-training/cert/lab.local/_wildcard.sys.lab.local-key.pem \
        -out keystore.sys.p12 \
        -name springboot-latency-api-sys \
        -passout pass:changeit
    - openssl pkcs12 -export \
        -in ../aws-devops-engineer-training/cert/lab.local/_wildcard.non-prod.lab.local.pem \
        -inkey ../aws-devops-engineer-training/cert/lab.local/_wildcard.non-prod.lab.local-key.pem \
        -out keystore.non-prod.p12 \
        -name springboot-latency-api-non-prod \
        -passout pass:changeit