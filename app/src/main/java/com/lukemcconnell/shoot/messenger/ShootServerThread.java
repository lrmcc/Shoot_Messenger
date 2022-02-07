/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import java.net.*;
import java.io.*;

public class ShootServerThread implements Runnable {
    private Socket socket = null;

    public ShootServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                                socket.getInputStream()));
            ) {
            String inputLine, outputLine;
            outputLine = ShootProtocol.response(null);
            out.println(outputLine);
                                    
            while ((inputLine = in.readLine()) != null) {
                outputLine = ShootProtocol.response(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Disconnecting client from server")){
                    break;
                }
            }
            //socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
