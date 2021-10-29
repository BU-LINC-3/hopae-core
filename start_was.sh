#!/bin/bash

docker pull qkdxorjs1002/tomcat:hopae
docker run --rm -itd --name tomcat_was -v /etc/localtime:/etc/localtime --net=host -p 1060:8080 qkdxorjs1002/tomcat:hopae
