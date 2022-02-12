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
    private static HashMap<String, ShootServerThread> shootServerThreads = new HashMap<String, ShootServerThread>();

    public ShootServer(String HOSTNAME, int PORT) {
        this.port = PORT;
    }

    private HashMap<String, ShootServerThread> getShootServerThreads(){
        return shootServerThreads;
    }

    private String getClientId(ShootServerThread connection){
        return connection.getUserInfo(1);
    }


    void registerThread(ShootServerThread shootServerThread){
        shootServerThreads.put(getClientId(shootServerThread), shootServerThread);
    }

    public void start() {
        System.out.println("ShootServer is starting!");

        boolean listening = true;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (listening) {
                ShootServerThread shootserverThread = new ShootServerThread(serverSocket.accept());
                Thread thread = new Thread(shootserverThread);
                thread.start();
                registerThread(shootserverThread);
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }
}
