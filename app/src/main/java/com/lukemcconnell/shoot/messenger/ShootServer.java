/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ShootServer manages application as a server.
 */
class ShootServer {

    private String localHostName;
    private boolean listening = false;
    private static volatile ArrayList<ServerThread> serverThreads = new ArrayList<>();

    /**
     * Returns HashMap of active ShootServerThread objects.
     * 
     * @return
     */
    ArrayList<ServerThread> getServerThreads(){
        return serverThreads;
    }

    /**
     * Constructor sets instance port.
     * 
     */
    ShootServer() {
        localHostName = ShootUtils.getLocalHostName();
        listening = true;
    }

    /**
     * Toggles listening to false, cause server listen loop to exit.
     * 
     */
    void stopListening(){listening = false;}
    
    /**
     * Returns a formatted String of current users for info output *ADD TIMESTAMP*.
     * 
     * @return
     */
    static String connectedUsers(){
        StringBuilder usernames = new StringBuilder();
        for (ServerThread serverThread: serverThreads){
            usernames.append(serverThread.getClientInfo()[0] + ", ");   
        }
        if (usernames.length() > 2)
            usernames.replace(usernames.length()-2, usernames.length()-1, ""); //removes last ', '
        return usernames.toString();
    }

    /**
     * Verifys each ServerThread object has a connected user.
     * Returns ArrayList with invalid threads omitted.
     * 
     * @return
     */
    static void verifyClients(){
        ArrayList<ServerThread> threadsToRemove = new ArrayList<>();
        for (ServerThread serverThread : serverThreads) {
            if (!serverThread.isLoggedIn())
                threadsToRemove.add(serverThread);
        }
        for (int i = 0; i < threadsToRemove.size(); i++){
            serverThreads.remove(threadsToRemove.get(i));
        }
        threadsToRemove.clear();
    }

     /**
     * Returns a String based on server evaluating client message.
     * 
     * @param input
     * @return
     */
    static String evaluate(String input) {
        String response = null;
        if (input.equals("exit"))
            response = "exiting app";
        else
            response = "Server received: " + input;
        return response;
    }

    /**
     * ShootServer main instance function for running server.
     * 
     */
    void start() {
        System.out.println("ShootServer is started on " + localHostName);

        try (ServerSocket serverSocket = new ServerSocket(ShootUtils.PORT)) {
            while (listening) {
                System.out.println("ShootServer is listening for connections on port: " + serverSocket.getLocalPort());
                Socket socket = serverSocket.accept();
                ServerThread shootServerThread = new ServerThread(socket);
                Thread thread = new Thread(shootServerThread);
                thread.start();
                synchronized (serverThreads) {
                    serverThreads.add(shootServerThread);
                }
                verifyClients();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + ShootUtils.PORT);
            System.exit(-1);
        }
    }
}
