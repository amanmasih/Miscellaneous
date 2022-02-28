FROM openjdk
WORKDIR usr/lib
ENV MONGO_DATABASE=productsdb
ENV MONGO_URL=mongodb://localhost:27017/productsdb
ADD ./target/userproductservice-0.0.1-SNAPSHOT.jar  /usr/lib/userproductservice-0.0.1-SNAPSHOT.jar
#run the jar file
ENTRYPOINT ["java","-jar","userproductservice-0.0.1-SNAPSHOT.jar"]