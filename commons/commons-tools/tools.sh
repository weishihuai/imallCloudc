#!/bin/bash
nohup java -Xms256m -Xmx768m -XX:PermSize=256m -XX:MaxPermSize=512m -Djava.ext.dirs=/opt/site/tools/lib -jar commons-tools-0.0.1-SNAPSHOT.jar  > tools_log.file 2>&1 &