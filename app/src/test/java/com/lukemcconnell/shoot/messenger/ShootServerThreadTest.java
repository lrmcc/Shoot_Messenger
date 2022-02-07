/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import java.io.*;
import java.net.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShootServerThreadTest {
    @Test void serverThreadObjectTypeTest() {
        Socket socket = null;
        ShootServerThread shootServerThreadTest = new ShootServerThread(socket);
        String serverThreadTestType = shootServerThreadTest.getClass().getName();
        System.out.println("serverThreadTestType.getClass().getName(): " + serverThreadTestType);
        assertSame(serverThreadTestType, shootServerThreadTest.getClass().getName());
    }
}
