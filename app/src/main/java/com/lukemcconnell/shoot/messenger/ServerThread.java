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

/**
 * ServerThread to handle individual client connection.
 */
class ServerThread implements Runnable {

    private Socket socket;
    private PrintWriter clientWriter;
    private BufferedReader serverReader;
    private boolean loggedIn = false;
    private String[] userInfo = new String[3];

    /**
     * Constructor sets instance socket to the socket object 
     * returned from ServerSocket serverSocket.accept().
     * 
     * @param socket
     */
    ServerThread(Socket sock) {
        socket = sock;
    }

    /**
     * Returns the ShootServerThread objects's socket object.
     * 
     * @return
     */
    Socket getSocket(){
        return socket;
    }

    /**
     * Returns boolean value indicating if the client has loggedIn.
     * 
     * @return
     */
    boolean isLoggedIn(){
        return loggedIn;
    }

    /**
     * Returns String array of client user info.
     * 
     * @return
     */
    String[] getClientInfo(){
        return userInfo;
    }

    /**
     * Takes in client info string and splits into array.
     * Returns String array of client info.
     * 
     * @param input
     * @throws IOException
     */
    void logIn() throws IOException {
        loggedIn = true;
        userInfo = serverReader.readLine().split(ShootUtils.SPLITMARKER);
        System.out.println("ServerThread login(): " + userInfo[0] + " has connected to the server");
    }

    /**
     * 
     */
    void logOut() {
        loggedIn = false;
    }

    void init() throws IOException {
        clientWriter = new PrintWriter(socket.getOutputStream(), true); // outgoing to client
        serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //incoming from client
    }

    void send(String serverMessage) {
        clientWriter.println(serverMessage);
    }

    /**
     * ShootServerThread main instace function.
     * 
     */
    public void run() {
        try {
            init();
            logIn();
            clientWriter.println("\nWelcome " + userInfo[0] + "!\n" + ShootServer.SERVER_INFO + "\n" + ShootServer.connectedUsers());
            String userInput;
            while (loggedIn && (userInput = serverReader.readLine()) != null) {
                System.out.println("Server Thread received: " + userInput);
                clientWriter.println("Server Thread received: " + userInput);
            }
            if (loggedIn) loggedIn = false;
            serverReader.close();
            clientWriter.close();
        } catch (IOException e) {
            System.out.println("ShootServerThreadError");
            e.printStackTrace(); 
        } finally {
            ShootServer.verifyClients();
        }
    }
}
