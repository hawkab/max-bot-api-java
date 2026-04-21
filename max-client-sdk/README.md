# Модуль "max-client-sdk"
[EN](README.en.md) | RU

## Структура
- `pom.xml` — генерация SDK через `openapi-generator-maven-plugin`
- `src/main/java/ru/golshansky/maxbotapi/client` — сгенерированные API, модели и invoker
- `src/main/java/ru/golshansky/maxbotapi/sdk` — ручная обвязка для конфигурации клиента через переменные окружения
- `src/main/resources/META-INF/spring` — автоконфигурация Spring Boot

## Переменные окружения
- `MAX_BOT_API_TOKEN` — обязательный токен MAX Bot API
- `MAX_BOT_API_BASE_URL` — базовый URL API, по умолчанию `https://platform-api.max.ru`

## Поведение при запуске
- если `MAX_BOT_API_TOKEN` не указан, при создании `MaxBotApiClient` будет выброшено исключение:
  - `Не задан токен MAX Bot API. Укажите переменную окружения MAX_BOT_API_TOKEN.`

## Использование в Spring Boot
После подключения модуля Spring Boot автоматически создаёт bean `MaxBotApiClient`.

Пример переменных окружения:
```bash
export MAX_BOT_API_TOKEN=your-token
export MAX_BOT_API_BASE_URL=https://platform-api.max.ru
```

## Ручное создание клиента
```java
var maxBotApiClient = MaxBotApiClientFactory.fromEnvironment();
```
[<< Назад](../README.md)