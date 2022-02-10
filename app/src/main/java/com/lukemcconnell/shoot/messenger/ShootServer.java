/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

public class ShootServer {

    private int port;
    private static HashMap<String, ShootClient> clients = new HashMap<String, ShootClient>();

    public ShootServer(String HOSTNAME, int PORT) {
        this.port = PORT;
    }

    void addClient(ShootClient client){
        clients.put(client.getUsername(), client);
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
