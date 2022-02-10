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
    private boolean status;

    public ShootClient(String HOSTNAME, int PORT) {
        this.hostname = HOSTNAME;
        this.port = PORT;
        this.status = true;
    }

    public boolean getStatus() {
        return this.status;
    }
    public String getUsername() {
        return this.username;
    }

    void clientLogin(BufferedReader stdIn, PrintWriter out){
        this.username = ShootUtils.getInput("username", stdIn);
        this.userId = ShootUtils.getRandomStr();
        this.userComputer = ShootUtils.getHostName();
        out.println(username + "::::" + userId + "::::" + userComputer + "::::");
    }

    void start() {
        System.out.println("ShootClient is starting!");

        try (
                Socket shootSocket = new Socket(hostname, port);
                PrintWriter out = new PrintWriter(shootSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(shootSocket.getInputStream()));) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            
            clientLogin(stdIn, out);

            String fromServer, fromUser;
            while (status) {
                if ((fromServer = in.readLine()).length() != 0) {
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
            System.err.println("Unknown host " + hostname + "/n" + e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("I/O exception for the connection to " + hostname + "/n" + e);
            System.exit(1);
        }
    }
}
