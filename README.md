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
