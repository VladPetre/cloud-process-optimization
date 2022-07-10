FROM --platform=linux/arm64 foxtrot.lan:5000/openjdk:11-jre

COPY cloud-process-optimization/cpo-gateway/target/cpo-gateway.jar /cpo-gateway.jar

CMD ["java", "-jar", "/cpo-gateway.jar"]
