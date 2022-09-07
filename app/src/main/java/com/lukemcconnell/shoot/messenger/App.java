/*
*  Shoot Messenger 0.0.1
*  Luke McConnell
*/
 
package com.lukemcconnell.shoot.messenger;
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class App {
 
   /**
    * Entry point to application, user selects mode of operation.
    *
    * @param args
    */
   public static void main(String[] args) {
       System.out.println("\nWelcome to Shoot Messenger!\n");
 
       try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
           String mode = ShootUtils.getInput("To create a server enter '1'\nTo connect as a client enter '2'\n", stdIn);
           if (mode.equals("1")) {
               ShootServer shootServer = new ShootServer();
               shootServer.start();
           }
           if (mode.equals("2")) {
               ShootClient shootClient = new ShootClient();
               shootClient.start();
           }
       } catch (Exception e) {
           System.err.println("Error:" + e);
           System.exit(-1);
       }
   }
}