package com.example.agentstudy.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** 第一个 HTTP 接口：验证项目可以正常接收请求并返回 JSON。 */
@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public Map<String, String> hello() {
        return Map.of(
                "application", "Agent_Study",
                "message", "Hello, Agent Study!");
    }
}
