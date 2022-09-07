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
import java.net.UnknownHostException;

/**
 * ShootServer manages application as a client.
 */
class ShootClient {

    private boolean loggedIn;
    private String username;
    private String userId;
    private String[] userInfo;
    private String userInfoStr;

    /**
     * Creates client object.
     * 
     */
    ShootClient() {
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
    void initUser(BufferedReader stdIn, PrintWriter socketOut) {
        username = ShootUtils.getInput("\nEnter a username:", stdIn);
        userId = ShootUtils.getRandomStr();
        userInfo = new String[]{username, userId, ShootUtils.getLocalHostName()};
        userInfoStr = username + ShootUtils.SPLITMARKER + userId + ShootUtils.SPLITMARKER + ShootUtils.getLocalHostName();
        socketOut.println(userInfoStr);
        loggedIn = true;
    }

    /**
     * Evaluates users input for valid messenger commands.
     * 
     * @param input
     * @return
     */
    String evaluateInput(String input) {
        if (input.equals("exit")) {
            return "Disconnecting client from server";
        }
        return input + " is an invalid flag";
    }

    /**
     * ShootClient main instance function.
     * 
     */
    void start() {
        System.out.println("ShootClient started!");

        try (
            // Socket connection to server.
            Socket socket = new Socket(ShootUtils.HOSTNAME, ShootUtils.PORT);
            // Reader from user.
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            // Writer to server.
            PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
            ) {
            
            initUser(stdIn, socketOut);
            System.out.println("After initUser() before ClientThread.start");

            ClientThread clientThread = new ClientThread(socket);
            Thread thread = new Thread(clientThread);
            thread.start();
            
            String userInput;
            while (loggedIn && (userInput = stdIn.readLine()) != null) {
                System.out.println("ShootClient user sent: " + userInput);
                // if (userInput.substring(0, 2).equals("--")) {
                //     userInput = evaluateInput(userInput.substring(2));
                // }
                // System.out.println("Client: " + userInput);
                socketOut.println(userInput);
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