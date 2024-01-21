#!/bin/sh
set -e

if [ "$#" -lt 1 ]; then
    echo "You have to pass ssh configuration for example 'ubuntu@ip_address -p <PORT>'"
    exit 1
fi

ssh_configuration="$1"

docker build -t real-time-calculations:latest ../ --no-cache

echo "Start uploading  real-time-calculations"
docker save real-time-calculations:latest | ssh -C "$ssh_configuration" docker load

ssh "$ssh_configuration" 'docker compose -f deploy/docker-compose-manual.yaml up -d real-time-calculations'