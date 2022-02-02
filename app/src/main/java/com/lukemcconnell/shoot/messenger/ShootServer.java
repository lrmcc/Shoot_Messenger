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
    
    public String getGreeting() {
        return "This is a ShootServer!";
    }

    public void start() {
        System.out.println("ShootServer is starting!");
    }
}