FROM tomcat:9.0.46
COPY ./* /usr/local/tomcat/repo/
RUN /usr/local/tomcat/repo/docker/batch.sh
WORKDIR /usr/local/tomcat/bin
# RUN apt update && apt install maven -y
# RUN mvn clean package
