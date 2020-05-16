#!/bin/sh

nohup java -Dfile.encoding=utf-8 -Dconfig=./server.config -Xmn128M -Xmx256M -jar switch-server-1.0-SNAPSHOT.jar > console.out 2>&1 &