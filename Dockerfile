FROM openjdk:17-alpine
COPY /target/marvel_superheroes_card-0.0.1-SNAPSHOT.jar marvel_card.jar
WORKDIR /marvel-app
ENTRYPOINT ["java","-jar","/marvel_card.jar"]