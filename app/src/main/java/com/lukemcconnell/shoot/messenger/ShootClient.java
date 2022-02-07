/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import java.io.*;
import java.net.*;

public class ShootClient {  
    public ShootClient() {
        setClientStatus(true);
    }
    private boolean clientStatus; 

    public boolean getClientStatus() {
        return clientStatus;
    }
    
    public void setClientStatus(boolean status) {
        this.clientStatus = status;
    }

    public void start(String hostname, int port) {
        System.out.println("ShootClient is starting!");

        try ( // try-with-resources to ensure each resource closed at end of statement
            Socket shootSocket = new Socket(hostname, port);
            PrintWriter out = new PrintWriter(shootSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(shootSocket.getInputStream()));
        ) {
            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while (getClientStatus()){
                if ((fromServer = in.readLine()) != null) {
                    if (fromServer.equals("Disconnecting client from server"))
                        break;
                    System.out.println("Server: " + fromServer);
                }
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host " + hostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("I/O exception for the connection to " +
                hostname);
            System.exit(1);
        }
    }
}
