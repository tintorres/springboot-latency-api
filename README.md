# springboot-latency-api
Springboot app to generate traffic for latency monitoring

# Maven 
* `Install` brew install maven
* `Check`   mvn --version
* `Compile`
    - Download Dependencies and compile
    - mvn clean compile
* `Run`
    - mvn spring-boot:run        
    - mvn spring-boot:run -Dspring-boot.run.profiles=dev        
    - mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8086"