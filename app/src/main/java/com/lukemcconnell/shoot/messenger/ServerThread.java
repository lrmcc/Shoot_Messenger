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
    void login(BufferedReader socketIn, PrintWriter socketOut) throws IOException {
        userInfo = socketIn.readLine().split(ShootUtils.SPLITMARKER);
        socketOut.println("Welcome " + userInfo[0] +"! Connected users are: " + ShootServer.connectedUsers());
        System.out.println(userInfo[0] + " has connected to the server");
        loggedIn = true;
    }

    /**
     * ShootServerThread main instace function.
     * 
     */
    public void run() {
        System.out.println("ServerThread running");
        try (
            PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true); // outgoing to client
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream())); //incoming from client
            ) {
            String userInput;
            String serverOutput;
            login(socketIn, socketOut);
            System.out.println("ServerThread after login");
            while (loggedIn && (userInput = socketIn.readLine()) != null) {
                System.out.println("Server Thread received: " + userInput);
                String response = ShootServer.evaluate(userInput);
                if (response.equals("exit")) break;
                serverOutput = "Server" + ShootUtils.SPLITMARKER + response;
                System.out.println("Server Thread response: " + serverOutput);
                socketOut.println(serverOutput);
            }
        } catch (IOException e) {
            System.out.println("ShootServerThreadError");
            e.printStackTrace();
        }
    }
}
