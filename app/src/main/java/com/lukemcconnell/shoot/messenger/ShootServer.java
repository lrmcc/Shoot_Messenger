/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import java.io.*;
import java.net.*;

public class ShootServer {
    
    String hostname;
    int port;  

    public ShootServer(String HOSTNAME, int PORT) {
        hostname = HOSTNAME;
        port = PORT;  
    }
    
    public void start(int port) {
        System.out.println("ShootServer is starting!");
    
            int portNumber = port;
            boolean listening = true;
            
            try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
                while (listening) {
                    new Thread(new ShootServerThread(serverSocket.accept())).start();
                }
            } catch (IOException e) {
                System.err.println("Could not listen on port " + portNumber);
                System.exit(-1);
            }
        }
    }

