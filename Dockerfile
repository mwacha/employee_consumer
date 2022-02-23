
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/mwacha/developer/employee_consumer/src
COPY pom.xml /home/mwacha/developer/employee_consumer
RUN mvn -f /home/mwacha/developer/employee_consumer/pom.xml clean package

#
# Package stage
ARG AWS_KEY
ARG AWS_SECRET_KEY
FROM openjdk:11
COPY --from=build /home/mwacha/developer/employee_consumer/target/employee_consumer.jar /usr/local/lib/employee_consumer.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/employee_consumer.jar"]