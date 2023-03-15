/*
*  Shoot Server 0.0.1
*  Luke McConnell
*/
 
package com.lukemcconnell.shoot.server;
 
 
public class App {

    /**
    * Entry point to Shoot Messenger server application.
    *
    * @param args
    */
    public static void main(String[] args) {
        System.out.println("\nLaunching Shoot Messenger server.\n");
 
        try {
            Server Server = new Server();
            Server.start();
       } catch (Exception e) {
           System.err.println("Error:" + e);
           System.exit(-1);
       }
   }

}