package com.lukemcconnell.shoot.server;

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
    private UserProfile userProfile;
    private boolean loggedIn = false;

    /**
     * Constructor sets instance socket to the socket object 
     * returned from ServerSocket serverSocket.accept().
     * 
     * @param socket
     */
    ServerThread(Socket sock) {
        socket = sock;
        userProfile = new UserProfile();
    }

    /**
     * Returns the ServerThread objects's socket object.
     * 
     * @return
     */
    Socket getSocket() {return socket;}

    /**
     * Returns UserProfile associated with ServerThread
     * 
     * @return
     */
    UserProfile getUserProfile() {return userProfile;}

    /**
     * Initializes ServerThread socket writer and reader.
     * 
     * @throws IOException
     */
    void init() throws IOException {
        clientWriter = new PrintWriter(socket.getOutputStream(), true); // outgoing to client
        serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //incoming from client
        userProfile.setLoggedIn(true);
        userProfile.setUserInfo(serverReader.readLine().split(Utils.SPLITMARKER));
        System.out.println(userProfile.getUserInfo()[0] + " has connected to the server");
        clientWriter.println("\nWelcome " + userProfile.getUserInfo()[0] + "!\n" + Utils.CONNECTION_INFO + "\n" + Server.getConnectedUsers());
    }

    /**
     * Sends message from main server thread to client.
     * 
     * @param serverMessage
     */
    void send(String serverMessage) {clientWriter.println(serverMessage);}

    /**
     * ServerThread main instace function.
     * 
     */
    public void run() {
        try {
            init();
            String userInput;
            while (loggedIn && (userInput = serverReader.readLine()) != null) {
                System.out.println("Server Thread received: " + userInput);
                clientWriter.println("Server Thread received: " + userInput);
            }
            if (loggedIn) loggedIn = false;
            serverReader.close();
            clientWriter.close();
        } catch (IOException e) {
            System.out.println("ServerThreadError");
            e.printStackTrace(); 
        } finally {
            Server.verifyClients();
        }
    }
}
