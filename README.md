# WebRise

**WebRise** — реактивное веб-приложение на основе Spring WebFlux и R2DBC, предоставляющее API для управления пользователями и их подписками.

## Основные возможности

* **Управление пользователями:**

    * Создание, получение, обновление и удаление пользователей.
* **Управление подписками:**

    * Добавление, получение и удаление подписок для пользователей.
* **Топ подписок:**

    * Получение списка самых популярных сервисов подписок (топ-5).
* **Реактивное API:**

    * Использует Spring WebFlux для асинхронной обработки запросов.
* **Документация API:**

    * Интеграция с Swagger UI для интерактивной документации.
* **Контейнеризация:**

    * Разворачивается с помощью Docker и Docker Compose.

## Технологии

* **Spring Boot:** 3.4.5
* **Spring WebFlux:** Для реактивного веб-API
* **Spring Data R2DBC:** Для реактивного доступа к PostgreSQL
* **PostgreSQL:** 17.5
* **R2DBC PostgreSQL Driver:** Для реактивного взаимодействия с PostgreSQL
* **Springdoc OpenAPI:** Для генерации документации API (Swagger UI)
* **Lombok:** Для упрощения кода
* **Docker & Docker Compose:** Для контейнеризации
* **Maven:** Для управления зависимостями
* **Java:** 17

## Структура проекта

```
webRise/
├── src/
│   ├── main/
│   │   ├── java/ru/dovakun/webRise/
│   │   │   ├── controller/              # Контроллеры (UserController, SubscriptionController, TopSubscriptionsController)
│   │   │   ├── dto/                     # DTO (UserDTO, SubscriptionDTO, TopSubscriptionDTO)
│   │   │   ├── entity/                  # Сущности (User, Subscription)
│   │   │   ├── repository/              # Репозитории R2DBC (UserRepository, SubscriptionRepository)
│   │   │   ├── service/                 # Сервисы (UserService, SubscriptionService)
│   │   │   └── WebRiseApplication.java  # Главный класс приложения
│   │   └── resources/
│   │       ├── application.yml          # Конфигурация приложения
│   │       └── schema.sql               # Схема базы данных
├── Dockerfile                           # Dockerfile для сборки приложения
├── docker-compose.yml                   # Конфигурация Docker Compose
├── pom.xml                              # Зависимости Maven
└── README.md                            # Документация проекта
```

## Установка и запуск

### Требования

* Docker и Docker Compose
* Java 17 (для локальной разработки)
* Maven (для локальной сборки)

### Шаги для запуска

1️⃣ Клонируйте репозиторий:

```bash
git clone https://github.com/<your-account>/webRise.git
cd webRise
```

2️⃣ Запустите приложение с Docker Compose:

```bash
docker-compose up --build
```

Это создаст и запустит два контейнера:

* **web-rise:** Приложение Spring Boot на порту `8080`
* **postgres:** База данных PostgreSQL на порту `5432`

3️⃣ Проверьте работу приложения:

* Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

4️⃣ Логи:

```bash
docker-compose logs web-rise
docker-compose logs postgres
```

5️⃣ Остановка приложения:

```bash
docker-compose down
```

## Локальная разработка (без Docker)

1️⃣ Запустите PostgreSQL локально или через Docker:

```bash
docker run -d --name postgres -p 5432:5432 \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=daster \
    -e POSTGRES_DB=usersubscriptions \
    postgres:17.5
```

2️⃣ Обновите `application.yml` для локального подключения:

```yaml
spring:
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/usersubscriptions
    username: postgres
    password: daster
```

3️⃣ Соберите и запустите приложение:

```bash
mvn clean install
mvn spring-boot:run
```

## Использование API

Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Основные эндпоинты

#### Пользователи

* `POST /users` — Создать пользователя

```json
{
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

* `GET /users/{id}` — Получить пользователя по ID
* `PUT /users/{id}` — Обновить пользователя
* `DELETE /users/{id}` — Удалить пользователя

#### Подписки

* `POST /users/{userId}/subscriptions` — Добавить подписку

```json
{
  "serviceName": "YouTube"
}
```

* `GET /users/{userId}/subscriptions` — Получить подписки пользователя
* `DELETE /users/{userId}/subscriptions/{subscriptionId}` — Удалить подписку

#### Топ подписок

* `GET /subscriptions/top` — Получить топ-5 популярных сервисов

```json
[
  {
    "serviceName": "YouTube",
    "count": 3
  },
  {
    "serviceName": "Netflix",
    "count": 2
  }
]
```

## Тестирование

Используйте Swagger UI или такие инструменты, как `curl` или Postman.

Пример запроса с `curl`:

```bash
curl -X POST http://localhost:8080/users \
     -H "Content-Type: application/json" \
     -d '{"name":"John Doe","email":"john.doe@example.com"}'
```

### Автоматические тесты

Для написания тестов добавьте зависимости в `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>io.projectreactor</groupId>
    <artifactId>reactor-test</artifactId>
    <scope>test</scope>
</dependency>
```

Включить подробное логирование R2DBC:

```yaml
logging:
  level:
    org.springframework.data.r2dbc: TRACE
```

## Лицензия

MIT License
