# Module "max-docs-generator"
EN | [RU](README.md)

## Structure
- `application` — spec generation use case
- `cli` — CLI entrypoint
- `config` — application settings
- `domain` — documentation DTOs
- `fetcher` — HTML HTTP fetching
- `parser` — HTML documentation parsing
- `openapi` — OpenAPI document building
- `output` — YAML writing
- `support` — shared parsing utilities

## Run
Via Maven:
```bash
mvn -pl max-docs-generator -am exec:java -Dexec.mainClass=ru.golshansky.maxbotapi.cli.MaxDocsSpecCli -Dexec.args="--output=target/generated-spec/max.openapi.yaml"
```

Via JAR:
```bash
java -jar max-docs-generator/target/max-docs-generator-0.0.1-SNAPSHOT.jar --output=target/generated-spec/max.openapi.yaml
```

## Parameters
- `--output=<path>`
- `--root-url=<url>`
- `--pause=<seconds>`

[<< Back](../README.en.md)