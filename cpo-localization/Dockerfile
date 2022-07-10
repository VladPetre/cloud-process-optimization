FROM --platform=linux/arm64 foxtrot.lan:5000/openjdk:11-jre

COPY cloud-process-optimization/cpo-localization/target/cpo-localization.jar /cpo-localization.jar

CMD ["java", "-jar", "/cpo-localization.jar"]
