# Java-Todo

A minimal **Spring Boot 3** todo-list web application demonstrating the canonical Spring Boot stack: Spring MVC, Spring Data JPA, Thymeleaf templating, an embedded H2 database, and Lombok for boilerplate-free entities.

> Built as a learning project on Java 21 + Spring Boot 3.4 to exercise the end-to-end "controller → repository → JPA → templated HTML" pattern.

---

## Stack

- **Java 21**
- **Spring Boot 3.4** (Web, Data JPA, Thymeleaf, Actuator, Validation, DevTools)
- **H2** — file-based embedded database (no external setup needed)
- **Lombok** — annotation-driven getters/setters
- **Bootstrap 4** for the front-end
- **Maven Wrapper** (`./mvnw`) — no global Maven install required

---

## Features

- Persists todo items in a local H2 database (`./data/todoapp`)
- Renders the list via a Thymeleaf template (`welcome.html`)
- Auto-creates the schema on startup (`spring.jpa.hibernate.ddl-auto=create`)
- Includes Spring Boot DevTools for hot reloading and Actuator for `/actuator/health`-style endpoints
- H2 console exposed at `/h2-console` for direct SQL inspection during development

---

## Project structure

```
.
├── pom.xml
├── mvnw / mvnw.cmd                                 # Maven Wrapper
└── src/main/
    ├── java/com/harshwkumar/java_todo/
    │   ├── JavaTodoApplication.java                # @SpringBootApplication entrypoint
    │   ├── controllers/
    │   │   └── PageController.java                 # GET / → renders welcome view
    │   ├── models/
    │   │   └── TodoItem.java                       # @Entity (id, description, completed, dateCreated, dateModified)
    │   └── repositories/
    │       └── TodoItemRepository.java             # JpaRepository<TodoItem, Long>
    └── resources/
        ├── application.properties                  # H2 + JPA + server config
        └── templates/
            └── welcome.html                        # Thymeleaf view
```

### Domain model

```java
@Entity
public class TodoItem {
    @Id @GeneratedValue Long id;
    String description;
    Boolean completed;
    Instant dateCreated;
    Instant dateModified;
}
```

The repository extends `JpaRepository<TodoItem, Long>`, so all standard CRUD operations (`findAll`, `save`, `deleteById`, etc.) are available without any boilerplate.

---

## Getting started

### Prerequisites

- **JDK 21** ([Adoptium Temurin](https://adoptium.net/temurin/releases/) recommended)
- Maven Wrapper is included — no separate Maven install needed

### Run

```bash
git clone https://github.com/Harshiitrpr/Java-Todo.git
cd Java-Todo

./mvnw spring-boot:run            # macOS / Linux
mvnw.cmd spring-boot:run          # Windows
```

Or, if you use VS Code, the **Spring Boot Dashboard** extension can start / stop the app from the IDE.

Then open:
- **App:** http://localhost:3000/
- **H2 console:** http://localhost:3000/h2-console
  (JDBC URL: `jdbc:h2:file:./data/todoapp` · user: `admin` · password: `admin`)

### Build a JAR

```bash
./mvnw clean package
java -jar target/java-todo-0.0.1-SNAPSHOT.jar
```

---

## Configuration

All settings live in [`src/main/resources/application.properties`](./src/main/resources/application.properties):

| Property | Default | Purpose |
|---|---|---|
| `server.port` | `3000` | HTTP port |
| `spring.datasource.url` | `jdbc:h2:file:./data/todoapp` | H2 file-based DB location |
| `spring.datasource.username` / `.password` | `admin` / `admin` | H2 credentials |
| `spring.jpa.hibernate.ddl-auto` | `create` | Drops + recreates schema on every startup (good for dev, **not** prod) |
| `spring.h2.console.enabled` | `true` | Enables `/h2-console` |

---

## What's next

Currently the controller seeds a single `"Hello World"` item on every request. Natural extensions:

- Add `POST /todos`, `PUT /todos/{id}`, `DELETE /todos/{id}` endpoints
- Form on `welcome.html` to create / toggle / delete items
- Switch `ddl-auto=create` to `update` (or use Liquibase / Flyway) so data persists across restarts
- Add unit + integration tests under `src/test/java/`
- Containerize with a `Dockerfile` and `docker-compose.yml`

---

## License

See [`pom.xml`](./pom.xml) — currently no explicit license set.
