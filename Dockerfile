FROM openjdk:11

ARG JAR_FILE=./target/books-1.0.0-SNAPSHOT-runner.jar

ARG PROFILE=dev

ENV PROFILE=${PROFILE}

COPY ${JAR_FILE} /opt/app.jar

ADD ./target/lib/* /opt/lib/

CMD ["java", "-jar", "-Dspring.profiles.active=dev", "/opt/app.jar"]
