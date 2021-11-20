#!/bin/bash
mvn -T 1C clean package -Dmaven.test.skip -DskipTests -f  /usr/src/mymaven  &&
find /usr/src/mymaven/target -name "*.war" -exec cp '{}' /usr/local/tomcat/webapps \;