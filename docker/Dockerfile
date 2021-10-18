FROM tomcat:9.0.46
COPY batch.sh /usr/local/tomcat/repo/docker.sh
RUN /usr/local/tomcat/repo/docker/batch.sh
WORKDIR /usr/local/tomcat/bin
# RUN apt update && apt install maven -y
# RUN mvn clean package
