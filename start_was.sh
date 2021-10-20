#!/bin/bash

#docker build --tag tomcat:9.0.46_1 ./docker
docker run --rm -itd --name tomcat_was -v /etc/localtime:/etc/localtime --net=host -p 1060:8080 tomcat:hopae
