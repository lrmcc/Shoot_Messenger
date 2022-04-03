/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import java.io.*;
import java.net.*;

public class ShootClient {

    private String hostname, username, userId, userComputer;
    private int port;
    private boolean status, loggedIn;

    /**
     * Returns boolean status of the client
     * @return
     */
    public boolean getStatus() {
        return this.status;
    }

    /**
     * Returns the username of the client
     * @return
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Constructor initializes instance attributes with hostname and port parameters passed
     * @param HOSTNAME
     * @param PORT
     */
    public ShootClient(String HOSTNAME, int PORT) {
        this.hostname = HOSTNAME;
        this.port = PORT;
        this.status = true;
        this.loggedIn = false;
    }

    /**
     * Constructor initializes instance attributes with no passed parameters
     * @param HOSTNAME
     * @param PORT
     */
    public ShootClient() {
        this.hostname = ShootUtils.hostname;
        this.port = ShootUtils.port;
        this.status = true;
        this.loggedIn = false;
    }

    /**
     * Sets client attributes for logging in.
     * Returns formatted string for server consumption.
     * @param username
     * @return
     */
    String login(String username) {
        this.username = username;
        this.userId = ShootUtils.getRandomStr();
        this.userComputer = ShootUtils.getHostName();
        return username + ShootUtils.splitMarker + userId + ShootUtils.splitMarker + userComputer;
    }   

    /**
     * Parses client message for valid commands
     * @param input
     * @return
     */
    String parseInput(String input) {
        if (input.equals("exit")) {
            return "Disconnecting client from server";
        }
        return input + " is an invalid flag";
    }

    /**
     * ShootClient main instance function
     */
    void start() {
        System.out.println("ShootClient is starting!");

        try (
                Socket shootSocket = new Socket(hostname, port);
                PrintWriter out = new PrintWriter(shootSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(shootSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                ) {

            String serverResponse, userInput;
            while (status) {
                if (!loggedIn){
                    String username = ShootUtils.getInput("username", stdIn);
                    out.println(login(username));
                    this.loggedIn = true;
                }
                while ((serverResponse = in.readLine()) != null){
                    String sender = ShootUtils.getStrFromSplit(serverResponse, 0);
                    String response = ShootUtils.getStrFromSplit(serverResponse, 1);
                    if (!(response.equals("none"))){
                        System.out.println(sender + ": " + response);
                    }
                    if (response.equals("Disconnecting client from server"))
                        break;

                    if ((userInput = stdIn.readLine()) != null) {
                        if (userInput.substring(0, 2).equals("--")) {
                            userInput = parseInput(userInput.substring(2));
                        }
                        
                        System.out.println("Client: " + userInput);
                        out.println(userInput);
                    }
                    
                }               
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host " + hostname + "/n" + e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("I/O exception for the connection to " + hostname + "/n" + e);
            System.exit(1);
        }
    }

    void exit(){
        System.out.println("ShootClient exiting!");
        System.exit(0);
    }

}