#!/bin/bash

cd $(dirname "$0")
docker-compose -p bookstore down -v --remove-orphans