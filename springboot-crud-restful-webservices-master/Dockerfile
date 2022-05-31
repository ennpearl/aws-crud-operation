FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /opt/app
EXPOSE 8080
ARG JAR_FILE=target/springboot-crud-restful-webservices-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} springboot-crud-restful-webservices-0.0.1-SNAPSHOTjar
ENTRYPOINT ["java","-jar","springboot-crud-restful-webservices-0.0.1-SNAPSHOT.jar"]
