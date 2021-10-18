FROM tomcat:9.0.46

WORKDIR /usr/local/tomcat/repo
COPY ./* ./

WORKDIR ./docker
RUN ./batch.sh

WORKDIR ../../bin
RUN ./shutdown.sh

ENTRYPOINT ./startup.sh

# RUN apt update && apt install maven -y
# RUN mvn clean package
