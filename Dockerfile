FROM openjdk:17-jdk-oraclelinux8

WORKDIR /app

ARG JAR_FILE

ENV S3_ACCESS_KEY_ID=${S3_ACCESS_KEY_ID}
ENV S3_SECRET_ACCESS_KEY=${S3_SECRET_ACCESS_KEY}

COPY target/${JAR_FILE} /app/algafood-api.jar

EXPOSE 8080

CMD ["java", "-jar", "algafood-api.jar"]