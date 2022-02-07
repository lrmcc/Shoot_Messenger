/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import java.io.*;
import java.net.*;

public class ShootServer {

    private String hostname;
    private int port;

    public ShootServer(String HOSTNAME, int PORT) {
        this.hostname = HOSTNAME;
        this.port = PORT;
    }

    public void start() {
        System.out.println("ShootServer is starting!");

        boolean listening = true;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (listening) {
                new Thread(new ShootServerThread(serverSocket.accept())).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }
}
