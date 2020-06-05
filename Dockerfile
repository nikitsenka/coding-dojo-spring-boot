FROM openjdk:11

RUN apt-get update && \
      apt-get -y install sudo
RUN useradd -m -s /bin/sh appuser && echo "appuser:appuser" | chpasswd && adduser appuser sudo

WORKDIR /home/appuser

COPY target/coding-dojo-spring-boot-*.jar app.jar
COPY src/main/resources config
RUN chown appuser /var/log

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
