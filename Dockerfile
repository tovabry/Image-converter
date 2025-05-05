FROM eclipse-temurin:24-jdk-alpine

WORKDIR /app

RUN mkdir -p runtime
RUN mkdir -p serviceconsumer/src/main/resources
RUN mkdir -p input
RUN mkdir -p output

COPY runtime/*.jar ./runtime/
COPY serviceconsumer/src/main/resources/*.jpg ./serviceconsumer/src/main/resources/
COPY serviceconsumer/src/main/resources/*.png ./serviceconsumer/src/main/resources/

RUN chmod -R 777 /app/input
RUN chmod -R 777 /app/output

ENTRYPOINT ["java", "--module-path", "/app/runtime", "--module", "se.whispers.modules.serviceconsumer/se.whispers.modules.consumer.Main"]