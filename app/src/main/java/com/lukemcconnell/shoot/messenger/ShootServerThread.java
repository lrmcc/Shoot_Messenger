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

public class ShootServerThread implements Runnable {
    private Socket socket = null;

    public ShootServerThread(Socket socket) {
        this.socket = socket;
    }

    String response(String input) {
        System.out.println("Server received: " + input);
        String response = parseInput(input);
        return response;
    }

    String parseInput(String input){
        if (input.equals("--exit")){
            return "Disconnecting client from server";
        }
        return "";
    }

    static String[] login(String input) {
        System.out.println("Server received: " + input);
        return input.split("::::", 3);
    }

    public void run() {
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                                socket.getInputStream()));
            ) {
            
            String inputLine, outputLine; 
            while ((inputLine = in.readLine()) != null) {
                outputLine = response(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Disconnecting client from server")){
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("ShootServerThreadError");
            e.printStackTrace();
        }
    }
}
