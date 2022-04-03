/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ShootServer {

    private int port;
    static ArrayList<ShootServerThread> shootServerThreads = new ArrayList<ShootServerThread>();

    /**
     * Returns HashMap of active ShootServerThread objects
     * @return
     */
    private static ArrayList<ShootServerThread> getShootServerThreads(){
        return shootServerThreads;
    }

    /**
     * Constructor sets instance port
     * @param HOSTNAME
     * @param PORT
     */
    public ShootServer(String HOSTNAME, int PORT) {
        this.port = PORT;
    }

    /**
     * Constructor sets instance port
     * @param HOSTNAME
     * @param PORT
     */
    public ShootServer() {
        this.port = ShootUtils.port;
    }
    
    /**
     * Returns a formatted String of current users for info output *ADD TIMESTAMP*
     * @param shootServerThreads
     * @return
     */
    static String getCurrentUsernames(){
        String usernames = "\n";
        for (ShootServerThread shootServerThread: shootServerThreads){
            try{
                usernames = usernames + shootServerThread.getClientInfo(0) + "\n";
            } catch(ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
                System.out.println("error retrieving username\nverifying all clients are connected\n");
                shootServerThreads = verifyClients();
            }
        }
        return usernames;
    }

    /**
     * Verifys each ShootServerThread has a valid client
     * Returns ArrayList with invalid threads omitted
     * @return
     */
    static ArrayList<ShootServerThread> verifyClients(){
        ArrayList<ShootServerThread> shootServerThreads = getShootServerThreads();
        for (int i = 0; i < shootServerThreads.size(); i ++){
            ShootServerThread serverThread = shootServerThreads.get(i);
            if (serverThread.getStatus() == false){
                shootServerThreads.remove(i);
            }
        } 
        return shootServerThreads;
    }

    /**
     * ShootServer main instance function for running server
     */
    public void start() {
        System.out.println("ShootServer is starting!");

        boolean listening = true;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (listening) {
                ShootServerThread shootServerThread = new ShootServerThread(serverSocket.accept());
                Thread thread = new Thread(shootServerThread);
                thread.start();
                shootServerThreads.add(shootServerThread);
                shootServerThreads = verifyClients();
                System.out.println(getCurrentUsernames());
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }
}
