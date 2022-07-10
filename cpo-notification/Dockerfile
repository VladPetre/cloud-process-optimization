FROM --platform=linux/arm64 foxtrot.lan:5000/openjdk:11-jre

COPY cloud-process-optimization/cpo-notification/target/cpo-notification.jar /cpo-notification.jar

CMD ["java", "-jar", "/cpo-notification.jar"]
