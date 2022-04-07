FROM adoptopenjdk/openjdk11

LABEL maintainer="Yared Negede"

#RUN mvn package

WORKDIR /workmotion

COPY ./target/challenge-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "./challenge-0.0.1-SNAPSHOT.jar"]