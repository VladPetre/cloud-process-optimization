FROM --platform=linux/arm64 foxtrot.lan:5000/openjdk:11-jre

COPY cloud-process-optimization/cpo-caller/target/cpo-caller.jar /cpo-caller.jar

CMD ["java", "-jar", "/cpo-caller.jar"]
