package com.lukemcconnell.shoot.messenger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShootClientTest {
    @Test void clientHasAGreeting() {
        ShootClient classUnderTest = new ShootClient("test", 5050);
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}
