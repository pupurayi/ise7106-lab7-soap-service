#!/bin/bash

name="ise7106-lab7-soap-service"
port=8080
while getopts e:p: flag
do
    # shellcheck disable=SC2220
    case "${flag}" in
        n) name=${OPTARG};;
        e) environment=${OPTARG};;
        p) port=${OPTARG};;
    esac
done

if [ -z "$environment" ]
then
  echo "environment is not defined user -e flag";
  exit 1
fi
echo "Deploying '$name' to '$environment' on port :'$port'";

if [ ! -d "/var/log/$name" ]
then
  mkdir "/var/log/$name" || {
    echo "Please run as sudo so that we can create directory '$name'"
    exit 1
  }
  chmod -R 777 "/var/log/$name"
fi

# Enable Docker BuildKit https://docs.docker.com/build/buildkit/#getting-started
DOCKER_BUILDKIT=1 docker build -t "$name" .
docker stop "$name"  || true && docker rm "$name"  || true
docker run -d -p $port:$port -p 5"$port":5"$port" --restart=always --add-host=host.docker.internal:host-gateway -v "/var/log/$name:/app/logs/$name" -e SPRING_PROFILES_ACTIVE="$environment" -e JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,address=5$port,server=y,suspend=n" --name "$name" "$name"