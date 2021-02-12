FROM openjdk:11
VOLUME /tmp
ADD ./target/hr-auth-0.0.1-SNAPSHOT.jar hr-auth.jar
ENTRYPOINT ["java","-jar","/hr-auth.jar"]
