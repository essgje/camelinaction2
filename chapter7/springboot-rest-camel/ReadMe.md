chapter7 - spring-boot-rest-camel
---------------------------------

Running Spring Boot and using Camel from a REST endpoint

### 7.2.4  Spring Boot with Camel as microservice 

You need to build this example first

    mvn install
    
And then you can run the example using
    
    mvn spring-boot:run
    
And from a web browser open the following url:

    http://localhost:8080/spring/hello

You can also run the example as a fat-jar using: 

    java -jar target/chapter7-springboot-rest-camel-2.0.0.jar
    
Notice this example uses online internet access to lookup your current location.
This is done using the camel-geocoder component.

