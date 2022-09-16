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
    void init(BufferedReader stdIn, PrintWriter socketOut) {
        loggedIn = true;
        username = ShootUtils.getInput("\nEnter a username:", stdIn);
        userId = ShootUtils.getRandomStr();
        userInfo = new String[]{username, userId, ShootUtils.getLocalHostName()};
        userInfoStr = username + ShootUtils.SPLITMARKER + userId + ShootUtils.SPLITMARKER + ShootUtils.getLocalHostName();
        socketOut.println(userInfoStr);
    }

    /**
     * ShootClient main instance function.
     * 
     */
    void start() {
        try (
            Socket socket = new Socket(ShootUtils.HOSTNAME, ShootUtils.PORT);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
            ) {
            System.out.println("Shoot Client launched from " + ShootUtils.CONNECTION_INFO);    
            init(stdIn, socketOut);
            ClientThread clientThread = new ClientThread(socket);
            Thread thread = new Thread(clientThread);
            thread.start();
            String userInput;
            while (loggedIn && (userInput = stdIn.readLine()) != null) {
                System.out.println("ShootClient user sent: " + userInput);
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