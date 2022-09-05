/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import java.io.*;
import java.net.*;

class ShootClient {

    private String username;
    private String userId;
    private boolean status, loggedIn;

    /**
     * Creates client object.
     * 
     */
    ShootClient() {
        this.status = true;
        this.loggedIn = false;
    }
    
    /**
     * Returns boolean status of the client.
     *
     * @return
     */
    boolean getStatus() {
        return this.status;
    }

    /**
     * Returns the userId of the client.
     * @return
     */
    String getUserId() {
        return this.userId;
    }

    /**
     * Returns the username of the client.
     * @return
     */
    String getUsername() {
        return this.username;
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
        return username + ShootUtils.SPLITMARKER + userId + ShootUtils.SPLITMARKER + ShootUtils.getLocalHostName();
    }   

    /**
     * Parses client message for valid commands.
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
     * ShootClient main instance function.
     */
    void start() {
        System.out.println("ShootClient is starting!");

        try (
                Socket shootSocket = new Socket(ShootUtils.HOSTNAME, ShootUtils.PORT);
                PrintWriter out = new PrintWriter(shootSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(shootSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                ) {

            String serverResponse, userInput;
            while (status) {
                if (!loggedIn){
                    String username = ShootUtils.getInput("\nEnter a username:", stdIn);
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
            System.err.println("Unknown host " + ShootUtils.HOSTNAME + "/n" + e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("I/O exception for the connection to " + ShootUtils.HOSTNAME + "/n" + e);
            System.exit(1);
        }
    }

    void exit() {
        System.out.println("ShootClient exiting!");
        System.exit(0);
    }

}