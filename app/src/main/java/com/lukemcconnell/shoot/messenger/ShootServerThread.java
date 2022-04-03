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
    private boolean status = false;
    private boolean loggedIn = false;
    private String[] userInfo = new String[3];
    private String serverName = "Server";

    /**
     * Returns the ShootServerThread objects's socket object
     * @return
     */
    public Socket getSocket(){
        return this.socket;
    }

    public boolean getStatus(){
        return this.status;
    }

    /**
     * Returns boolean value indicating if the client has loggedIn
     * @return
     */
    public boolean getLoggedIn(){
        return this.loggedIn;
    }

    /**
     * Returns String array of client user info
     * @return
     */
    public String[] getClientInfo(){
        return this.userInfo;
    }

    /**
     * 
     * Given an index, returns String element of user info String array
     * @param idx
     * @return
     */
    public String getClientInfo(int idx){
        return this.userInfo[idx];
    }

    /**
     * Constructor sets instance socket to the socket object returned from ServerSocket serverSocket.accept()
     * @param socket
     */
    public ShootServerThread(Socket socket) {
        this.socket = socket;
        this.status = true;
    }

    /**
     * Returns a String based on server evaluating client message
     * @param input
     * @return
     */
    String response(String input) {
        String response = "none";
        if (!getLoggedIn()) {
            this.userInfo = login(input);
            response = "Welcome " + getClientInfo(0) + "!";
        }
        if (input.substring(0, 2).equals("--")) {
            System.out.println("calling parseInput in ShootServerThread");
            response = parseInput(input.substring(2));
        }
        return response;
    }

    /**
     * Parses client message for valid commands
     * @param input
     * @return
     */
    String parseInput(String input) {
        String parseResponse = "";
        System.out.println("parseInput(String input), input: " + input);
        if (input.equals("users")) {
            parseResponse = ShootServer.getCurrentUsernames();
        }
        else if (input.equals("exit")) {
            parseResponse = "Disconnecting client from server";
        }
        else{
            parseResponse = input + " is an invalid flag!";
        }
        return parseResponse;
    }

    /**
     * Takes in client info string and splits into array.
     * Returns String array of client info 
     * @param input
     * @return
     */
    String[] login(String input) {
        this.loggedIn = true;
        this.userInfo = input.split(ShootUtils.splitMarker);
        return getClientInfo();
    }

    /**
     * ShootServerThread main instace function 
     */
    public void run() {
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ) {
            System.out.println("New ShootServer thread run() ");
            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Server received: " + inputLine);
                String response = response(inputLine);
                if (response.equals("Disconnecting client from server")) {
                    System.out.println("---- Disconnecting client from server ----");
                    this.status = false;
                    break;
                }
                outputLine = serverName + ShootUtils.splitMarker + response;
                System.out.println("Server response: " + outputLine);
                out.println(outputLine);
            }
        } catch (IOException e) {
            System.out.println("ShootServerThreadError");
            e.printStackTrace();
        }
    }
}
