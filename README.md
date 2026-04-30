# kojuju-coupon 模版

> 这是一个 **参数化模板工程**，设计目标是：上传到工程自动化平台后，可以通过模板参数把固定内容渲染成真正的新项目。

这是一个可直接作为脚手架基础的 **Spring Boot + MyBatis-Plus** 模版，内置：

- 示例控制器（Controller）
- 示例实体（Entity）
- 示例 Mapper
- 示例 Service
- 三套环境配置：`dev` / `staging` / `prd`
- 开箱即跑的 H2 本地开发环境
- 可切换到 MySQL 的预留配置

当前模板已经参数化的关键变量：

- `coupon`：项目编码 / artifactId / 默认应用名
- `kojuju-coupon`：项目展示名称
- `com.kojuju.coupon`：Java 包名，例如 `com.company.springbootdemo`
- `com/kojuju/coupon`：Java 包路径，例如 `com/company/springbootdemo`

---

## 1. 技术栈

- Java 17
- Spring Boot 3.3.x
- MyBatis-Plus 3.5.x
- H2（dev 示例库）
- MySQL（staging / prd 示例配置）

---

## 2. 目录结构

```text
template
├─ pom.xml
├─ README.md
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ com/kojuju/coupon
   │  │     ├─ SpringMybatisPlusTemplateApplication.java
   │  │     ├─ common
   │  │     │  ├─ api
   │  │     │  │  └─ ApiResponse.java
   │  │     │  └─ config
   │  │     │     └─ MybatisPlusConfig.java
   │  │     └─ user
   │  │        ├─ controller
   │  │        │  └─ UserController.java
   │  │        ├─ dto
   │  │        │  ├─ CreateUserRequest.java
   │  │        │  └─ UserView.java
   │  │        ├─ entity
   │  │        │  └─ UserEntity.java
   │  │        ├─ mapper
   │  │        │  └─ UserMapper.java
   │  │        └─ service
   │  │           └─ UserService.java
   │  └─ resources
   │     ├─ application.yml
   │     ├─ application-dev.yml
   │     ├─ application-staging.yml
   │     ├─ application-prd.yml
   │     └─ db
   │        ├─ schema-h2.sql
   │        └─ data-h2.sql
   └─ test
      └─ java
         └─ com/kojuju/coupon
            └─ SpringMybatisPlusTemplateApplicationTests.java
```

---

## 3. 模版包含了什么

### 3.1 Controller 示例

路径：`src/main/java/com/example/template/user/controller/UserController.java`

提供了 3 个接口：

- `GET /api/v1/users`：查询用户列表
- `GET /api/v1/users/{id}`：查询单个用户
- `POST /api/v1/users`：新增用户

### 3.2 Entity 示例

路径：`src/main/java/com/example/template/user/entity/UserEntity.java`

示例内容包括：

- `@TableName`
- `@TableId`
- `@TableField`
- `createdAt / updatedAt` 自动填充字段

### 3.3 Mapper 示例

路径：`src/main/java/com/example/template/user/mapper/UserMapper.java`

使用 `BaseMapper<UserEntity>`，不写 XML 也能完成基础 CRUD。

### 3.4 Service 示例

路径：`src/main/java/com/example/template/user/service/UserService.java`

演示了：

- 查询列表
- 按 ID 查询
- 新增数据

### 3.5 项目配置

资源目录包含 4 个配置文件：

- `application.yml`：公共配置
- `application-dev.yml`：本地开发环境
- `application-staging.yml`：测试 / 预发环境
- `application-prd.yml`：生产环境

---

## 4. 环境说明

### 4.1 dev

本地默认激活 `dev`：

- 使用 H2 内存数据库
- 启动时自动执行 `schema-h2.sql` 和 `data-h2.sql`
- 可以直接启动看到示例数据
- MyBatis SQL 日志默认打开

适合：

- 模版演示
- 本地开发
- 脚手架验证

### 4.2 staging

`staging` 配置预留了 MySQL 连接信息，默认通过环境变量覆盖：

- `DB_HOST`
- `DB_PORT`
- `DB_NAME`
- `DB_USERNAME`
- `DB_PASSWORD`

适合：

- 测试环境
- 联调环境
- 预发布环境

### 4.3 prd

`prd` 与 `staging` 类似，也通过环境变量读取 MySQL 配置。

不同点：

- 更保守的日志级别
- 默认不自动初始化数据库

适合：

- 生产环境部署

---

## 5. 启动方式

### 5.1 本地启动（dev）

如果本机安装了 Maven：

```bash
mvn spring-boot:run
```

默认会使用：

```text
spring.profiles.active=dev
```

启动成功后访问：

- 应用地址：`http://localhost:8080`
- H2 控制台：`http://localhost:8080/h2-console`

H2 默认连接信息：

```text
JDBC URL: jdbc:h2:mem:coupon_db;MODE=MYSQL;DATABASE_TO_LOWER=TRUE;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
User Name: sa
Password: 空
```

### 5.2 指定环境启动

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=staging
```

或：

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prd
```

---

## 6. 示例接口

### 6.1 查询列表

```bash
curl http://localhost:8080/api/v1/users
```

### 6.2 查询单个用户

```bash
curl http://localhost:8080/api/v1/users/1
```

### 6.3 新增用户

```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d "{\"userName\":\"new-user\",\"email\":\"new.user@example.com\"}"
```

---

## 7. 如何把它改造成你自己的业务模版

建议按下面顺序替换：

1. 在平台里为 `projectCode / projectName / basePackage` 等参数填写真实值
2. ZIP 模板渲染后，`src/main/java/com/kojuju/coupon` 会自动变成真实包路径
3. 删除 `user` 示例模块，替换成你的真实模块
4. 将 H2 初始化 SQL 改成你的业务表结构
5. 根据实际数据库修改 `staging/prd` 的连接配置
6. 如果你有统一返回结构、异常处理、审计字段规范，可以在 `common` 目录继续扩展

---

## 8. 配置建议

### 8.1 推荐保留的基础结构

- `common/api`：统一返回结构
- `common/config`：框架级配置
- `xxx/controller`：接口层
- `xxx/service`：业务层
- `xxx/entity`：实体层
- `xxx/mapper`：数据访问层
- `resources/application-*.yml`：环境隔离配置

### 8.2 数据库脚本管理建议

如果后续要演进成正式项目，建议接入：

- Flyway 或 Liquibase 做数据库版本化

当前模版为了开箱即跑，先使用了简单的 SQL 初始化方式。

---

## 9. 注意事项

1. 该模版默认是 **Java 17**。
2. `dev` 环境使用 H2，只用于本地示例。
3. `staging / prd` 请务必改成真实数据库配置。
4. 该模版已经包含基础 Controller / Entity / Mapper 示例，可以直接复制扩展。
