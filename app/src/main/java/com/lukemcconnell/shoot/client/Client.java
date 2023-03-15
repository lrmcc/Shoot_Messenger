package com.lukemcconnell.shoot.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Client application class.
 */
class Client {

    private boolean loggedIn;
    private String username;
    private String userId;
    private String[] userInfo;
    private String userInfoStr;

    /**
     * Creates client object.
     * 
     */
    Client() {
        loggedIn = false;
    }

    /**
     * Returns the userId of the client.
     * 
     * @return
     */
    String getUserId() {
        return userId;
    }

    /**
     * Returns the username of the client.
     * 
     * @return
     */
    String getUsername() {
        return username;
    }

    /**
     * Returns userInfo array.
     * 
     * @return
     */
    String[] getUserInfo() {
        return userInfo;
    }
    /**
     * Returns userInfo array formatted into string with split marker.
     * 
     * @return
     */
    String getUserInfoStr() {
        return userInfoStr;
    }

    /**
     * Sets client attributes for logging in.
     * Returns formatted string for server consumption.
     * 
     * @param stdIn
     * @throws IOException
     */
    void init(BufferedReader stdIn, PrintWriter socketOut) {
        loggedIn = true;
        username = Utils.getInput("\nEnter a username:", stdIn);
        userId = Utils.getRandomStr();
        userInfo = new String[]{username, userId, Utils.getLocalHostName()};
        userInfoStr = username + Utils.SPLITMARKER + userId + Utils.SPLITMARKER + Utils.getLocalHostName();
        socketOut.println(userInfoStr);
    }

    /**
     * Client main instance function.
     * 
     */
    void start() {
        try (
            Socket socket = new Socket(Utils.HOSTNAME, Utils.PORT);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
            ) {
            System.out.println("Shoot Client launched from " + Utils.CONNECTION_INFO);    
            init(stdIn, socketOut);
            ClientThread clientThread = new ClientThread(socket);
            Thread thread = new Thread(clientThread);
            thread.start();
            String userInput;
            while (loggedIn && (userInput = stdIn.readLine()) != null) {
                System.out.println("Client user sent: " + userInput);
                socketOut.println(userInput);
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host " + Utils.HOSTNAME + "/n" + e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("I/O exception for the connection to " + Utils.HOSTNAME + "/n" + e);
            System.exit(1);
        } 
    }

    void exit() {
        System.out.println("Client exiting!");
        System.exit(0);
    }

}