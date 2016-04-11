# Nebula - Kotlin / Java backend service

## Setup

Install necessary tooling via `brew`:

    brew cask install java
    brew install kotlin
    brew install gradle

### IntelliJ IDEA

- Download IDEA CE 16
- Install Kotlin plugin
- Import Project > From existing model > Gradle
- Specify GRADLE_HOME = /usr/local/Cellar/gradle/GRADLE_VERSION/libexec

### Install Docker

Used for building and running distributable images

    brew install docker docker-machine
    docker-machine create --driver virtualbox default
    eval $(docker-machine env default)

## Developing

Build:

    gradle assemble

Run tests and code checkers:

    gradle check

## Running

    gradle run
    http://localhost:8080/health

For remote debugging (will pause until debugger connected):

    gradle run --debug-jvm

Of course you could just run with debugging from IDEA.

## Deploying

Build image at desired version:

    gradle -PnebulaVersion=1.0 image

Push and run:

    docker run -d -p 8080:8080 --name nebula jmailen/nebula:1.0
    docker logs -f nebula
    http://DOCKER_MACHINE_IP:8080/health
    docker stop nebula
    docker rm nebula
