FROM adoptopenjdk/openjdk11

LABEL maintainer="Yared Negede"

RUN mkdir /workmotion

WORKDIR /workmotion

#COPY ./entrypoint.sh .

EXPOSE 8080

#RUN chmod -R 777 entrypoint.sh

CMD ["java", "-jar", "challenge-0.0.1-SNAPSHOT.jar"]









