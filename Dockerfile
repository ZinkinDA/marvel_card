FROM openjdk:17-alpine
COPY /target/marvel_superheroes_card-0.0.1-SNAPSHOT.jar marvel_card.jar
ENTRYPOINT ["java","-jar","/jwt_project.jar"]