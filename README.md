# English Book Reading Assistant

[![ilyavy](https://circleci.com/gh/ilyavy/lingualeo-book-reading-assistant.svg?style=svg)](https://circleci.com/gh/ilyavy/lingualeo-book-reading-assistant)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ilyavy_lingualeo-book-reading-assistant&metric=coverage)](https://sonarcloud.io/dashboard?id=ilyavy_lingualeo-book-reading-assistant)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=ilyavy_lingualeo-book-reading-assistant&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=ilyavy_lingualeo-book-reading-assistant)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=ilyavy_lingualeo-book-reading-assistant&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=ilyavy_lingualeo-book-reading-assistant)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=ilyavy_lingualeo-book-reading-assistant&metric=security_rating)](https://sonarcloud.io/dashboard?id=ilyavy_lingualeo-book-reading-assistant)

The GUI desktop application, allows to analyze English books and add the most frequently used in the text words into https://lingualeo.com dictionary for further learning.

It is supposed that it will help to learn the most frequently used words before reading the book, to make reading experience more smooth and enjoyable.

**Supported book formats:**
- plain text (`.txt`).

## Usage
The application is distributed via a *jar-file*.

### Running pre-built binary
It can be downloaded from the [releases](https://github.com/ilyavy/lingualeo-book-reading-assistant/releases) tab in the repository. And then launched from the directory, where it was downloaded, using the command (for the latest release):
```shell
java --enable-preview -jar lingualeo-book-reading-assistant-1.0.0.jar
```
The application will start in a new window.

### Building from sources
In order to build the executable *jar-file* from the sources, it's needed to use the command:
```shell
mvn package
```
The application then can be run from `target` directory with the command:
```shell
java --enable-preview -jar lingualeo-book-reading-assistant.jar
```

## Project's structure
```
├── src
    └── main      # source-set of the application
    └── perf      # source-set of the performance benchmark
└── book-samples  # books samples
```
