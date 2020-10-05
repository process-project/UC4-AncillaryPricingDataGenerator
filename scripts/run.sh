#!/bin/bash

HADOOP_OUTPUT_FOLDER="response/"

docker rm -f hdfs
docker rm -f generator
docker rm -f generatorOutput
docker rm -f calculator

docker-compose -f ../docker-compose.yml up -d hdfs

hadoopIP=$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' hdfs)

bash ./wait-for-it.sh -h $hadoopIP -p 8020 -t 15

docker exec -it hdfs hdfs dfs -mkdir -p $HADOOP_OUTPUT_FOLDER
docker exec -it hdfs hdfs dfs -chmod 777 $HADOOP_OUTPUT_FOLDER

docker-compose -f ../docker-compose.yml up -d generator

sleep 6

docker-compose -f ../docker-compose.yml up -d generatorOutput

sleep 6

docker-compose -f ../docker-compose.yml up -d calculator 