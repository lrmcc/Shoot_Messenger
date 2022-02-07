/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/
package com.lukemcconnell.shoot.messenger;

public class ShootProtocol {

    public static String response(String input) {
        System.out.println("Server received: " + input);
        return "Server is processing the message";
    }
}
