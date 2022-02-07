/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShootProtocolTest {
    @Test void serverResponseTest() {
        assertEquals(ShootProtocol.response("test"), "Server is processing the message");
    }
}
