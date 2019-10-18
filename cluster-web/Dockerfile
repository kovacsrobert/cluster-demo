FROM gradle:4.7.0-jdk8-alpine AS build
COPY --chown=gradle:gradle . ${projectDir}/cluster-web
WORKDIR ${projectDir}/cluster-web
RUN gradle build docker --no-daemon

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=./build/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","hello.web.Application"]