package com.apress.todo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import redis.embedded.RedisServer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TodoRedisApplicationTests {

    private static RedisServer redisServer;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @BeforeAll
    static void startRedis() throws Exception {
        // Start embedded Redis on default port 6379
        redisServer = new RedisServer(6379);
        redisServer.start();
        System.out.println("Embedded Redis started on port 6379");
    }

    @AfterAll
    static void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
            System.out.println("Embedded Redis stopped");
        }
    }

    @Test
    void testRedisConnection() {
        // No need to set host/port dynamically
        redisTemplate.opsForValue().set("todoKey", "Test Redis");
        String value = (String) redisTemplate.opsForValue().get("todoKey");
        assertEquals("Test Redis", value);
    }

    @Test
    void contextLoads() {
        // Ensures Spring Boot context loads successfully
    }
}
