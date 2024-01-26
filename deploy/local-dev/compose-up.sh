#!/bin/bash

cd $(dirname "$0")
docker-compose -p bookstore up -d --build --remove-orphans