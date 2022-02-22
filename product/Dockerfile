
FROM openjdk
#create a working directory inside the image
WORKDIR usr/lib
#setting environment variable same name as in applciation properties
ENV MONGO_DATABASE=productdb
ENV MONGO_URL=mongodb://localhost:27017/productdb
#copy executable jar file to the working directory
ADD ./target/product-0.0.1-SNAPSHOT.jar  /usr/lib/product-0.0.1-SNAPSHOT.jar
#run the jar file
ENTRYPOINT ["java","-jar","product-0.0.1-SNAPSHOT.jar"]