# max-bot-api-java
EN | [RU](README.md)

[![Support this project · TON network](https://hawkab.github.io/support/support-button.svg)](https://hawkab.github.io/support/)

Optional contributions support development, maintenance and testing. The [support page](https://hawkab.github.io/support/) has a QR code, wallet link and copy buttons, and works on computers and phones. You choose the amount in your wallet.

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