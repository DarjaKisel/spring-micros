#!/usr/bin/env bash
set -xe

./build-services.sh
./build-docker.sh

docker compose up --force-recreate --remove-orphans --build -d

docker image prune -a --force --filter "until=24h"