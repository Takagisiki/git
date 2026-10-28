# Agent_Study

Java + AI Agent 学习项目，从一个可以运行的 Spring Boot Web 服务开始。

## 环境

- JDK 21
- Spring Boot 4.1.2
- Maven Wrapper（不需要另外安装 Maven）
- Spring Web MVC、Validation、Spring Boot Test

## 在 IDEA 中运行

1. 打开此目录或 `pom.xml`，将其作为 Maven 项目导入。
2. 确保 Project SDK 和 Maven 的运行 JDK 为 21，等待依赖下载完成。
3. 打开 `src/main/java/com/example/agentstudy/AgentStudyApplication.java`。
4. 点击 `main` 方法旁的绿色运行按钮。
5. 浏览器访问 <http://localhost:8080/api/hello>。

示例响应（字段顺序可能不同）：

```json
{"application":"Agent_Study","message":"Hello, Agent Study!"}
```

## 命令行运行

在项目目录的 PowerShell 中执行：

```powershell
.\mvnw.cmd spring-boot:run
```

构建和测试：

```powershell
.\mvnw.cmd verify
```

如果 8080 端口已被占用，可在 `src/main/resources/application.properties` 中设置 `server.port=8081`，并相应更改访问地址。

## 代码位置

- `AgentStudyApplication.java`：程序入口。
- `controller/HelloController.java`：第一个 HTTP 接口。
- `src/main/resources/application.properties`：应用配置。
- `src/test/java`：测试代码。

## 下一步

先学习请求如何经过 Controller、Service 和数据库并返回 JSON，然后逐步加入订单管理、模型调用、RAG 和工具调用。
