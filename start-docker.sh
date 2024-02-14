#!/usr/bin/env bash

cd ./env || exit

docker-compose rm

docker-compose up -d