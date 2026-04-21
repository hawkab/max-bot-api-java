# Module "max-client-sdk"
EN | [RU](README.md)

## Structure
- `pom.xml` — SDK generation via `openapi-generator-maven-plugin`
- `src/main/java/ru/golshansky/maxbotapi/client` — generated APIs, models and invoker
- `src/main/java/ru/golshansky/maxbotapi/sdk` — handwritten client bootstrap from environment variables
- `src/main/resources/META-INF/spring` — Spring Boot auto-configuration

## Environment variables
- `MAX_BOT_API_TOKEN` — required MAX Bot API token
- `MAX_BOT_API_BASE_URL` — API base URL, defaults to `https://platform-api.max.ru`

## Startup behavior
- if `MAX_BOT_API_TOKEN` is missing, `MaxBotApiClient` creation fails with:
  - `Не задан токен MAX Bot API. Укажите переменную окружения MAX_BOT_API_TOKEN.`

## Spring Boot usage
After adding the module, Spring Boot automatically creates a `MaxBotApiClient` bean.

Example environment variables:
```bash
export MAX_BOT_API_TOKEN=your-token
export MAX_BOT_API_BASE_URL=https://platform-api.max.ru
```

## Manual client creation
```java
var maxBotApiClient = MaxBotApiClientFactory.fromEnvironment();
```

[<< Back](../README.en.md)