FROM openjdk
WORKDIR usr/src
EXPOSE 8085
ADD ./target/userauthenticationservice-0.0.1-SNAPSHOT.jar /usr/src/userauthenticationservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","userauthenticationservice-0.0.1-SNAPSHOT.jar"]