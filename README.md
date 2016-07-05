# Nebula - Example Kotlin / Java Spring Boot Service

[![Build Status](https://travis-ci.org/jeremymailen/nebula.svg?branch=master)](https://travis-ci.org/jeremymailen/nebula)

Demonstrates how Kotlin improves developing in the Java ecosystem.

This project goes beyond the typical demo to answer what it might look like to build a Java ecosystem service in Kotlin which leverages popular frameworks and tools:
- Spring Boot and Spring MVC
- JUnit and Mockito
- Gradle and Docker

Objective is to demonstrate not only compatibility but more concise, safe, and expressive code. Caveat: I'm just learning Kotlin.

## Setup

Assuming an OSX development environment. Reasonable equivalents available on Linux and Windows.

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

Used for building and running distributable images (optional).

    brew install docker docker-machine
    docker-machine create --driver virtualbox default
    eval $(docker-machine env default)

## Developing

Build:

    gradle assemble

Run tests and code checkers:

    gradle check

### Using Live Reload

Server and client can be reloaded as you make code changes. After `gradle run`, in a separate terminal run a watch build:

    gradle assemble --continuous

The server will be reloaded by Spring Boot devtools when new built artifacts are produced by `assemble`.

The web client will also refresh if you install the [Chrome LiveReload Extension](https://chrome.google.com/webstore/search/livereload?hl=en).

## Running

    gradle run
    http://localhost:8002/api/health
    http://localhost:8002/api/profiles
    http://localhost:8002/api/profiles/1

For remote debugging (will pause until debugger connected):

    gradle run --debug-jvm

Of course you could just run with debugging from IDEA.

## Deploying

Build image at desired version:

    gradle -Pversion=1.0 image

Push and run:

    docker run -d -p 8002:8002 --name nebula jmailen/nebula:1.0
    docker logs -f nebula
    http://DOCKER_MACHINE_IP:8002/api/health
    docker stop nebula
    docker rm nebula
