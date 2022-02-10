/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to Shoot Messenger!");

        String hostname = "localhost";
        int port = 5050;
        try(
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ){
        String mode = ShootUtils.getInput("mode ('server' or '1' for server, 'client' or '2' for client):", stdIn);
        System.out.println(mode + " mode!");

        if (mode.equals("server") || mode.equals("1") ) {
            ShootServer shootServer = new ShootServer(hostname, port);
            System.out.println("ShootServer is about to start!");
            shootServer.start();
        }
        if (mode.equals("client") || mode.equals("2")) {
            ShootClient shootClient = new ShootClient(hostname, port);
            System.out.println("ShootClient is about to start!");
            shootClient.start();
        }
        }catch (Exception e) {
            System.err.println("Error:" + e);
            System.exit(-1);
        }
    }
}
