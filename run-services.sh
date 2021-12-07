#!/usr/bin/env bash

set -ex

docker build --build-arg JAR_FILE=./build/libs/\*.jar -t palerique/social-rating-data-collector -f src/main/docker/Dockerfile .