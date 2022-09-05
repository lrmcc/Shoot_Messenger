/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;

class ShootServer {

    private int port;
    private String localHostName;
    private boolean listening = false;
    private ArrayList<ShootServerThread> shootServerThreads = new ArrayList<>();

    /**
     * Returns HashMap of active ShootServerThread objects.
     * @return
     */
    ArrayList<ShootServerThread> getShootServerThreads(){
        return shootServerThreads;
    }

    /**
     * Constructor sets instance port.
     */
    ShootServer() {
        port = ShootUtils.PORT;
        localHostName = ShootUtils.getLocalHostName();
        listening = true;
    }

    /**
     * Toggles listening to false, cause server listen loop to exit.
     */
    void stopListening(){listening = false;}
    
    /**
     * Returns a formatted String of current users for info output *ADD TIMESTAMP*.
     * @param shootServerThreads
     * @return
     */
    String getCurrentUsernames(){
        StringBuilder usernames = new StringBuilder();
        System.out.println("Number of ShootServerThreads: " + shootServerThreads.size());
        for (ShootServerThread shootServerThread: shootServerThreads){
            try{
                usernames.append("\n" + Arrays.toString(shootServerThread.getClientInfo()) + "\n");
            } catch(ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
                System.out.println("error retrieving username\nverifying all clients are connected\n");
                shootServerThreads = verifyClients();
            }
        }
        return usernames.toString();
    }

    /**
     * Verifys each ShootServerThread has client with a connected user.
     * Returns ArrayList with invalid threads omitted.
     * @return
     */
    ArrayList<ShootServerThread> verifyClients(){
        for (ShootServerThread shootServerThread : shootServerThreads) {
            if (!shootServerThread.getStatus())
                shootServerThreads.remove(shootServerThread);
        } 
        return shootServerThreads;
    }

    /**
     * ShootServer main instance function for running server.
     */
    void start() {
        System.out.println("ShootServer is starting on " + this.localHostName);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (listening) {
                ShootServerThread shootServerThread = new ShootServerThread(serverSocket.accept());
                Thread thread = new Thread(shootServerThread);
                thread.start();
                shootServerThreads.add(shootServerThread);
                shootServerThreads = verifyClients();
                System.out.println("Usernames: " + getCurrentUsernames());
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }
}
