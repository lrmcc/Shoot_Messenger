/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ShootServerThread implements Runnable {
    private Socket socket;
    private boolean loggedIn = false;
    private String[] userInfo = new String[3];
    private String serverName = "Server";
    public static final String splitMarker = "::::";

    public Socket getSocket(){
        return this.socket;
    }
    public boolean getLoggedIn(){
        return this.loggedIn;
    }
    
    public String[] getUserInfo(){
        return this.userInfo;
    }
    public String getUserInfo(int idx){
        return this.userInfo[idx];
    }

    public ShootServerThread(Socket socket) {
        this.socket = socket;
    }

    String response(String input) {
        String response = "none";
        if (!getLoggedIn()) {
            this.loggedIn = true;
            userInfo = input.split(splitMarker);
            response = "Welcome " + userInfo[0] + "!";
        }
        if (input.substring(0, 2).equals("--")) {
            response = parseInput(input.substring(2));
        }
        return response;
    }

    String parseInput(String input) {
        if (input.equals("exit")) {
            return serverName + "Disconnecting client from server";
        }
        return input + " is an invalid flag";
    }

    String[] login(String input) {
        System.out.println("Server received: " + input);
        return input.split("::::", 3);
    }

    public void run() {
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ) {
            System.out.println("New ShootServer thread run() ");
            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Server received: " + inputLine);
                outputLine = serverName + splitMarker + response(inputLine);
                System.out.println("Server response: " + outputLine);
                out.println(outputLine);
                if (outputLine.equals("Disconnecting client from server")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("ShootServerThreadError");
            e.printStackTrace();
        }
    }
}
