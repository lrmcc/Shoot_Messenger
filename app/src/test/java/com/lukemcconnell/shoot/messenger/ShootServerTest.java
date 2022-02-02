package com.lukemcconnell.shoot.messenger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShootServerTest {
    @Test void serverHasAGreeting() {
        ShootServer classUnderTest = new ShootServer("test", 5050);
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}
