/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShootClientTest {
    @Test void serverObjectTypeTest() {
        String hostname = "localhost";
        int port = 5050;
        ShootClient shootClientTest = new ShootClient(hostname, port);
        String shootClientTestType = shootClientTest.getClass().getName();
        System.out.println("shootServerTest.getClass().getName(): " + shootClientTestType);
        assertSame(shootClientTestType, shootClientTest.getClass().getName());
    }
}
