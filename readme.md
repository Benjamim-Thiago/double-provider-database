# 📘 Documentação Técnica - Projeto Spring Boot com Múltiplos Bancos de Dados

## 📌 Objetivo

Este projeto tem como objetivo demonstrar como integrar múltiplos bancos de dados em uma aplicação Spring Boot:
- **MySQL** usando **Spring Data JPA**.
- **SQL Server** usando **JDBC puro**.

---

## 🧱 Estrutura do Projeto

```
com.multiples_dbs.ftd_multiples_db
│
├── Application.java
├── DataConsumerRunner.java
├── config
│   └── db
│       ├── MysqlJpaConfig.java
│       ├── SqlServerDataSourceFactory.java
│       └── model_config
│           ├── MysqlDataSourceProperties.java
│           └── SqlServerDataSourceProperties.java
├── domain
│   ├── mysql
│   │   ├── entity
│   │   │   ├── Profile.java
│   │   │   └── User.java
│   │   ├── repository
│   │   │   ├── ProfileRepository.java
│   │   │   └── UserRepository.java
│   │   └── service
│   │       ├── ProfileService.java
│   │       └── UserService.java
│   └── sqlserver
│       ├── entity
│       │   └── Post.java
│       ├── repository
│       │   └── PostRepository.java
│       └── service
│           └── PostService.java
```

---

## ⚙️ Configuração

### 🔹 application.yml.example

```yaml
spring:
  application:
    name:
datasource:
  mysql:
    url: jdbc:mysql://localhost:3306/db
    username: user
    password: pass
    driver-class-name: com.mysql.cj.jdbc.Driver
  sqlserver:
    url: jdbc:sqlserver://localhost:1433;databaseName=db
    username: user
    password: pass
    driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver
```

> ⚠️ Certifique-se de preencher os valores no `application.yml` real e mantê-lo fora do versionamento.

---

## 🧩 Camada de Configuração (`config.db`)

A camada `config.db` é responsável por configurar as **fontes de dados** (`DataSource`) e **gerenciar a persistência** de forma separada para cada banco.

### 📁 `config.db.mode_config`

#### 🔸 MysqlDataSourceProperties.java

Lê as configurações do `application.yml` para o MySQL.

#### 🔸 SqlServerDataSourceProperties.java

Lê as configurações do `application.yml` para o SQL Server.

### 📁 `config.db`

#### 🔹 MysqlJpaConfig.java

Classe de configuração JPA para MySQL:
- Define beans de `DataSource`, `EntityManagerFactory`, e `TransactionManager`.
- Utiliza `@EnableJpaRepositories` e `@EntityScan`.

```java
@EnableJpaRepositories(
  basePackages = "com.multiples_dbs.ftd_multiples_db.domain.mysql.repository",
  entityManagerFactoryRef = "mysqlEntityManagerFactory",
  transactionManagerRef = "mysqlTransactionManager")
```

#### 🔹 SqlServerDataSourceFactory.java

Fábrica para criar `DataSource` do SQL Server manualmente.

```java
public static DataSource createDataSource(SqlServerDataSourceProperties properties)
```

---

## 🛠 Configuração dos Bancos

### ✅ MySQL via JPA

Configurado com:
- `@EnableJpaRepositories`
- `@EntityScan`
- `LocalContainerEntityManagerFactoryBean`
- `JpaTransactionManager`

Classe responsável: `MysqlJpaConfig.java`

### ✅ SQL Server via JDBC

Configurado com:
- `DriverManagerDataSource`
- Classe `SqlServerDataSourceFactory`

Sem uso de JPA, usando `Connection`, `PreparedStatement` etc.

---

## 🗃️ Entidades

### MySQL

- `User` (com relacionamento `@ManyToOne` com `Profile`)
- `Profile`

### SQL Server

- `Post` (classe POJO manipulada via JDBC)

---

## 📦 Repositórios

### MySQL (JPA)
- `UserRepository`
- `ProfileRepository`

### SQL Server (JDBC)
- `PostRepository`

---

## 💼 Serviços

- `UserService`, `ProfileService` — realizam operações com JPA.
- `PostService` — realiza operações com JDBC.

---

## ▶️ Execução

A classe `DataConsumerRunner` é executada automaticamente ao iniciar a aplicação, por implementar `CommandLineRunner`.

Exemplo de uso:

```java
System.out.println(profileService.getProfileById(4));
System.out.println(userService.getUser(1));

DataSource sqlserverDataSource =
    SqlServerDataSourceFactory.createDataSource(sqlServerProperties);
PostRepository postRepo = new PostRepository(sqlserverDataSource);
PostService postService = new PostService(postRepo);

System.out.println(postService.getPostsByUserId(1));
```

---

## ✅ Considerações Técnicas

- `@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})` impede a autoconfiguração única de `DataSource`.
- Cada banco possui seu próprio contexto de transações.
- JDBC foi utilizado para SQL Server com abordagem mais manual e flexível.
- `@ConfigurationProperties` carrega as credenciais dos bancos via YAML.

---