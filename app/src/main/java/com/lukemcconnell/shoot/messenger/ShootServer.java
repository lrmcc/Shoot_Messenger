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

    private boolean listening = false;
    private static ArrayList<ServerThread> serverThreads = new ArrayList<>();
    static final String SERVER_INFO = "ShootServer running on " + ShootUtils.getLocalHostName() + " on domain " + ShootUtils.HOSTNAME + " listening on port " + ShootUtils.PORT;

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
        usernames.append(serverThreads.size() + " connected users: ");
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
    static void verifyClients() {
        ArrayList<ServerThread> threadsToRemove = new ArrayList<>();
        synchronized (serverThreads) {
            for (ServerThread serverThread : serverThreads) {
                if (!serverThread.isLoggedIn())
                    threadsToRemove.add(serverThread);
            }
        }
        for (int i = 0; i < threadsToRemove.size(); i++) {
            serverThreads.remove(threadsToRemove.get(i));
        }
        threadsToRemove.clear();   
    }

    /**
     * ShootServer main instance function for running server.
     * 
     */
    void start() {
        System.out.println(SERVER_INFO);
        try (ServerSocket serverSocket = new ServerSocket(ShootUtils.PORT)) {
            while (listening) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                Thread thread = new Thread(serverThread);
                thread.start();
                serverThreads.add(serverThread);
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + ShootUtils.PORT);
            System.exit(-1);
        }
    }
}
