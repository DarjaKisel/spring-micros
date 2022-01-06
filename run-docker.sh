#!/usr/bin/env bash
set -xe

./build-services.sh
./build-docker.sh

docker compose up --force-recreate --remove-orphans --build -d

docker rmi $(docker images --filter "dangling=true" -q)