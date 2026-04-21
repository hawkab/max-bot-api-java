# max-bot-api-java
EN | [RU](README.md)

## Requirements
- JDK 26
- Maven 3.9+
- Access to `https://dev.max.ru/docs-api`

## Modules
- [max-docs-generator](max-docs-generator/README.en.md) — parses MAX documentation and generates `OpenAPI YAML`
- [max-client-sdk](max-client-sdk/README.en.md) — generates Java SDK from the produced specification

## Build
```bash
mvn clean install
```