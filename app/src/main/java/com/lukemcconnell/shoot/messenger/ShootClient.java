package com.lukemcconnell.shoot.messenger;

import java.io.*;
import java.net.*;

public class ShootClient {
    
    String hostname;
    int port;  
    
    public ShootClient(String HOSTNAME, int PORT) {
        hostname = HOSTNAME;
        port = PORT; 
    }
    
    public String getGreeting() {
        return "This is a ShootClient!";
    }

    public void start() {
        System.out.println("ShootClient is starting!");
    }
}
