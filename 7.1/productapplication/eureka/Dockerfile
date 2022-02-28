FROM openjdk
WORKDIR usr/lib
ADD ./target/eureka-0.0.1-SNAPSHOT.jar  /usr/lib/eureka-0.0.1-SNAPSHOT.jar
#run the jar file
ENTRYPOINT ["java","-jar","eureka-0.0.1-SNAPSHOT.jar"]