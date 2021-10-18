FROM tomcat:9.0.46

WORKDIR /usr/local/tomcat/repo
COPY ./* ./

WORKDIR /usr/local/tomcat/repo/docker
CMD ./batch.sh

WORKDIR /usr/local/tomcat/bin

# RUN apt update && apt install maven -y
# RUN mvn clean package
