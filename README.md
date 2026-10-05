# max-bot-api-java
[EN](README.en.md) | RU

Данный проект - практический [пример](https://github.com/hawkab/CodegenDemo) генерации SDK на основе спецификации,
появился как развитие [проекта парсинга и конвертации в openapi-формат](https://hawkab.github.io/max-messenger-swagger/) 
онлайн-документации https://dev.max.ru/docs-api 

[![Поддержать проект · TON network](https://hawkab.github.io/support/support-button.svg)](https://hawkab.github.io/support/)

Необязательные взносы помогают в разработке, сопровождении и тестировании. На [странице поддержки](https://hawkab.github.io/support/) есть QR-код, ссылка на кошелёк и кнопки копирования — работает на компьютере и телефоне. Сумму вы выбираете в кошельке.

## Требования
- JDK 26
- Maven 3.9+
- Доступ к `https://dev.max.ru/docs-api`

## Модули
- [max-docs-generator](max-docs-generator/README.md) - парсинг документации MAX и генерация `OpenAPI YAML`
- [max-client-sdk](max-client-sdk/README.md) - генерация Java SDK из готовой спецификации

## Сборка
```bash
mvn clean install
```