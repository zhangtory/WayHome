#!/bin/sh

rm -f tpid

nohup java -jar way-home-client-1.2.jar > /dev/null 2>&1 &

echo $! > tpid

echo Start Success!
