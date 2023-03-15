/*
*  Shoot Client 0.0.1
*  Luke McConnell
*/
 
package com.lukemcconnell.shoot.client ;
 
public class App {
 
   /**
    * Entry point to Shoot Messenger client application.
    *
    * @param args
    */
   public static void main(String[] args) {
        System.out.println("\nWelcome to Shoot Messenger!\n");
        try {
            Client Client = new Client();
            Client.start();
       } catch (Exception e) {
           System.err.println("Error:" + e);
           System.exit(-1);
       }
   }

}