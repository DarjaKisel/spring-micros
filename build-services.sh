#!/usr/bin/env bash
set -ex

for serviceDirectory in discovery config service-producer
#for serviceDirectory in discovery config service-producer service-consumer gateway
do
 pushd ./$serviceDirectory
# ./gradlew clean check bootJar
 ./gradlew clean bootJar
 popd
done
