package com.lukemcconnell.shoot.server;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServerTest {
    @Test void serverObjectTypeTest() {
        Server ServerTest = new Server();
        String ServerTestType = ServerTest.getClass().getName();
        System.out.println("ServerTest.getClass().getName(): " + ServerTestType);
        assertSame(ServerTestType, ServerTest.getClass().getName());
    }
}
