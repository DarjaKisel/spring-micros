#!/usr/bin/env bash
set -xe

for serviceName in discovery config service-producer gateway
#for serviceName in discovery config service-producer service-consumer gateway
do
 pushd ./$serviceName
 docker build --build-arg JAR_FILE=build/libs/\*.jar -t dzinevich/$serviceName -f ./Dockerfile .
 popd
done
