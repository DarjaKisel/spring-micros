#!/usr/bin/env bash
set -ex

for serviceDirectory in gateway
do
 pushd ./$serviceDirectory
 ./gradlew clean check bootJar
 popd
done
