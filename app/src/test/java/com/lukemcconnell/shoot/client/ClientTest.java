/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientTest {

    private static Client ClientTest = new Client();

    // @Test
    // void ClientStatusTest() {
    //     assertTrue(ClientTest.getStatus());
    // }

    // @Test void ClientLoginTest(){
    //     String[] loginTestArr = {"Luke", "e70357", "Lukes-Mac-mini.local"};
    //     String loginStrActual = ClientTest.login("Luke");
    //     String[] loginStrActualArr = loginStrActual.split("::::");
    //     assertEquals(loginStrActualArr[0], loginTestArr[0]);
    //     assertEquals(loginStrActualArr[2], loginTestArr[2]);
    // }
}
