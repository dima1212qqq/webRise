WebRise
WebRise — это реактивное веб-приложение на основе Spring WebFlux и R2DBC, предоставляющее API для управления пользователями и их подписками. Приложение позволяет создавать, обновлять, удалять пользователей, добавлять и удалять подписки, а также получать список самых популярных сервисов подписок. Проект использует PostgreSQL для хранения данных и разворачивается в Docker для упрощения разработки и деплоя.
Основные возможности

Управление пользователями: Создание, получение, обновление и удаление пользователей.
Управление подписками: Добавление, получение и удаление подписок для пользователей.
Топ подписок: Получение списка самых популярных сервисов подписок (топ-5).
Реактивное API: Использует Spring WebFlux для асинхронной обработки запросов.
Документация API: Интеграция с Swagger UI для интерактивной документации.
Контейнеризация: Разворачивается с помощью Docker и Docker Compose.

Технологии

Spring Boot: 3.4.5
Spring WebFlux: Для реактивного веб-API
Spring Data R2DBC: Для реактивного доступа к PostgreSQL
PostgreSQL: 17.5 (база данных)
R2DBC PostgreSQL Driver: Для реактивного взаимодействия с PostgreSQL
Springdoc OpenAPI: Для генерации документации API (Swagger UI)
Lombok: Для упрощения кода
Docker & Docker Compose: Для контейнеризации
Maven: Для управления зависимостями
Java: 17

Структура проекта
webRise/
├── src/
│   ├── main/
│   │   ├── java/ru/dovakun/webRise/
│   │   │   ├── controller/         # Контроллеры WebFlux (UserController, SubscriptionController, TopSubscriptionsController)
│   │   │   ├── dto/               # DTO (UserDTO, SubscriptionDTO, TopSubscriptionDTO)
│   │   │   ├── entity/            # Сущности (User, Subscription)
│   │   │   ├── repository/        # Репозитории R2DBC (UserRepository, SubscriptionRepository)
│   │   │   ├── service/           # Сервисы (UserService, SubscriptionService)
│   │   │   └── WebRiseApplication.java # Главный класс приложения
│   │   └── resources/
│   │       ├── application.yml    # Конфигурация приложения
│   │       └── schema.sql         # Схема базы данных
├── Dockerfile                     # Dockerfile для сборки приложения
├── docker-compose.yml             # Конфигурация Docker Compose
├── pom.xml                        # Зависимости Maven
└── README.md                      # Документация проекта

Установка и запуск
Требования

Docker и Docker Compose
Java 17 (для локальной разработки)
Maven (для локальной сборки)

Шаги для запуска

Клонируйте репозиторий:

[git clone https://github.com/<your-username>/webRise.git](https://github.com/dima1212qqq/webRise.git)
cd webRise


Запустите приложение с Docker Compose:

docker-compose up --build

Это создаст и запустит два контейнера:

web-rise: Приложение Spring Boot на порту 8080.
postgres: База данных PostgreSQL на порту 5432.


Проверьте, что приложение работает:


Откройте Swagger UI: http://localhost:8080/swagger-ui.html
Проверьте логи:

docker-compose logs web-rise
docker-compose logs postgres


Остановка приложения:

docker-compose down

Локальная разработка (без Docker)

Убедитесь, что PostgreSQL запущен локально или в Docker:

docker run -d --name postgres -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=daster -e POSTGRES_DB=usersubscriptions postgres:17.5


Обновите application.yml для локального подключения:

spring:
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/usersubscriptions
    username: postgres
    password: daster


Соберите и запустите приложение:

mvn clean install
mvn spring-boot:run

Использование API
API документировано через Swagger UI, доступно по адресу: http://localhost:8080/swagger-ui.html.
Основные эндпоинты
Пользователи

POST /users — Создать пользователя{
  "name": "John Doe",
  "email": "john.doe@example.com"
}


GET /users/{id} — Получить пользователя по ID
PUT /users/{id} — Обновить пользователя
DELETE /users/{id} — Удалить пользователя

Подписки

POST /users/{userId}/subscriptions — Добавить подписку{
  "serviceName": "YouTube"
}


GET /users/{userId}/subscriptions — Получить подписки пользователя
DELETE /users/{userId}/subscriptions/{subscriptionId} — Удалить подписку

Топ подписок

GET /subscriptions/top — Получить топ-5 популярных сервисов[
  {
    "serviceName": "YouTube",
    "count": 3
  },
  {
    "serviceName": "Netflix",
    "count": 2
  }
]



Тестирование
Для тестирования используйте Swagger UI или инструменты, такие как curl или Postman. Пример запроса:
curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{"name":"John Doe","email":"john.doe@example.com"}'

Для написания автоматических тестов добавьте зависимости в pom.xml:
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


logging:
  level:
    org.springframework.data.r2dbc: TRACE




Лицензия
MIT License
