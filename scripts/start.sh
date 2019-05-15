#!/bin/bash

docker rm -f hadoop
docker rm -f generator

docker network ls | grep docker-network > /dev/null || docker network create docker-network

docker run --net=docker-network -d -p 8020:8020 -p 8032:8032 -p 8088:8088 -p 9000:9000 -p 10020:10020 -p 19888:19888 -p 50010:50010 -p 50020:50020 -p 50070:50070 -p 50075:50075 -p 50090:50090 --name hadoop harisekhon/hadoop:2.9

docker run --net=docker-network -e HDFS_URL='hadoop' --name generator process/datagenerator

until [ "`/usr/bin/docker inspect -f {{.State.Running}} generator`"=="true" ]; do
    sleep 0.1;
done;

mkdir response
docker run --net=docker-network  -e HDFS_URL='hadoop' -e OUTPUT_PATH='/pricing-response' -v $(pwd)/response:/pricing-response -t ancillary-calculator