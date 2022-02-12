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
    private String splitMarker = "::::";

    public ShootClient(String HOSTNAME, int PORT) {
        this.hostname = HOSTNAME;
        this.port = PORT;
        this.status = true;
        this.loggedIn = false;
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getUsername() {
        return this.username;
    }


    String clientLogin(String username) {
        this.username = username;
        this.userId = ShootUtils.getRandomStr();
        this.userComputer = ShootUtils.getHostName();
        return username + splitMarker + userId + splitMarker + userComputer;
    }

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
                    out.println(clientLogin(username));
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
                        if (userInput.equals("--exitz")){
                            System.out.println("ShootClient exiting!");
                        };
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
}
