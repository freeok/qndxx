# 项目必须用jdk8
FROM openjdk:8u322-oracle
ADD ./target/qndxx-0.0.1-SNAPSHOT.jar qndxx.jar
ENTRYPOINT ["java", "-jar", "qndxx.jar"]
# 设置容器时区为东八区，不写这句部署后的时间和本地时间相差8小时，会导致很多bug
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
EXPOSE 8080
