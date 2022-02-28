FROM openjdk
WORKDIR usr/src
EXPOSE 9000
ADD ./target/apigateway-0.0.1-SNAPSHOT.jar /usr/src/apigateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","apigateway-0.0.1-SNAPSHOT.jar"]