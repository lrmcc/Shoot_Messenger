package com.lukemcconnell.shoot.messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread implements Runnable {

    private Socket socket;

    /**
     * Constructor sets instance socket to the socket object 
     * returned from ShootClient creating socket
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
        System.out.println("ClientThread running");
        try (
            // Reader from server.
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ) {
            String serverResponse;
            System.out.println("ClientThread before loop");
            while ((serverResponse = socketIn.readLine()) != null) {
                System.out.println("Client Thread received: " + serverResponse);
            }
        } catch (IOException e) {
            System.out.println("ClientThreadError");
            e.printStackTrace();
        }
    }
    
}
