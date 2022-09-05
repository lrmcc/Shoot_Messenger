/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShootServerTest {
    @Test void serverObjectTypeTest() {
        ShootServer shootServerTest = new ShootServer();
        String shootServerTestType = shootServerTest.getClass().getName();
        System.out.println("shootServerTest.getClass().getName(): " + shootServerTestType);
        assertSame(shootServerTestType, shootServerTest.getClass().getName());
    }
}
