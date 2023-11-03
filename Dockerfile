FROM openjdk:17-jdk-oraclelinux8

WORKDIR /app

COPY target/*.jar /app/algafood-api.jar

EXPOSE 8080

CMD ["java", "-jar", "algafood-api.jar"]