FROM --platform=linux/arm64 foxtrot.lan:5000/openjdk:11-jre

COPY cloud-process-optimization/cpo-enhancer/target/cpo-enhancer.jar /cpo-enhancer.jar

CMD ["java", "-jar", "/cpo-enhancer.jar"]
