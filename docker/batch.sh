#!/bin/bash

echo '<?xml version="1.0" encoding="UTF-8"?>
<tomcat-users xmlns="http://tomcat.apache.org/xml"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://tomcat.apache.org/xml tomcat-users.xsd"
              version="1.0">
  <role rolename="manager-gui"/>
  <user username="tomcat" password="hopaetomcat" roles="manager-gui"/>
</tomcat-users>' > /usr/local/tomcat/conf/tomcat-users.xml

echo '<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <servers>
    <server>
      <id>TomcatWAS</id>
      <username>tomcat</username>
      <password>hopaetomcat</password>
    </server>
  </servers>
</settings>' > /usr/share/maven/conf/settings.xml
