FROM openjdk:11
VOLUME /tmp
EXPOSE 8765
ADD ./target/hr-apigateway-zuul-0.0.1-SNAPSHOT.jar hr-apigateway-zuul.jar
ENTRYPOINT ["java","-jar","/hr-apigateway-zuul.jar"]