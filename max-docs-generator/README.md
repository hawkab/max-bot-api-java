# Модуль "max-docs-generator"
[EN](README.en.md) | RU

## Структура
- `application` — use case генерации спецификации
- `cli` — CLI-точка входа
- `config` — настройки приложения
- `domain` — DTO модели документации
- `fetcher` — HTTP-загрузка HTML
- `parser` — парсинг HTML документации
- `openapi` — сборка спецификации OpenAPI
- `output` — запись YAML
- `support` — общие утилиты парсинга

## Запуск
Через Maven:
```bash
mvn -pl max-docs-generator -am exec:java -Dexec.mainClass=ru.golshansky.maxbotapi.cli.MaxDocsSpecCli -Dexec.args="--output=target/generated-spec/max.openapi.yaml"
```

Через JAR:
```bash
java -jar max-docs-generator/target/max-docs-generator-0.0.1-SNAPSHOT.jar --output=target/generated-spec/max.openapi.yaml
```

## Параметры
- `--output=<path>`
- `--root-url=<url>`
- `--pause=<seconds>`

[<< Назад](../README.md)