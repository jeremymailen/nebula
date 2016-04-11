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
- Specify GRADLE_HOME = /usr/local/Cellar/gradle/<version>/libexec

## Developing

    gradle assemble
    gradle test

## Running

    gradle run
    http://localhost:8080/health
