FROM maven:3.8.3-openjdk-11 as builder

WORKDIR /usr/src/maven
COPY . .

RUN --mount=type=cache,target=/root/.m2 mvn clean package -f . && mkdir /usr/src/wars/
RUN find . -iname '*.war' -exec cp {} /usr/src/wars/ \;

FROM tomcat:9.0.54-jre8
COPY --from=builder /usr/src/wars/* /usr/local/tomcat/webapps/
COPY tomcat-users.xml $CATALINA_HOME/conf/
