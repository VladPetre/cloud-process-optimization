FROM --platform=linux/arm64 foxtrot.lan:5000/openjdk:11-jre

COPY cloud-process-optimization/cpo-processor/target/cpo-processor.jar /cpo-processor.jar

CMD ["java", "-jar", "/cpo-processor.jar"]
