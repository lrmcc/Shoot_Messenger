package com.lukemcconnell.shoot.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread implements Runnable {

    private Socket socket;

    /**
     * Constructor sets instance socket to the socket object
     * returned from Client creating socket
     * 
     * @param socket
     */
    ClientThread(Socket sock) {
        socket = sock;
    }

    /**
     * ClientThread to handle incoming messenges from server.
     * 
     */
    public void run() {
        try (
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            String messengeFromServer;
            while ((messengeFromServer = socketIn.readLine()) != null) {
                System.out.println(messengeFromServer);
            }
        } catch (IOException e) {
            System.out.println("ClientThreadError");
            e.printStackTrace();
        }
    }

}
