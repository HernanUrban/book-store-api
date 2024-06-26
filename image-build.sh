#!/bin/bash

# Extract version from pom.xml
VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
NAME=$(mvn help:evaluate -Dexpression=project.artifactId -q -DforceStdout)
echo "Building Docker image: $NAME with version: $VERSION"

# Build the Docker image
docker build -t hurban/$NAME:$VERSION .

# Optionally, push the Docker image to your repository
# docker push your-docker-repo/your-app:$VERSION
