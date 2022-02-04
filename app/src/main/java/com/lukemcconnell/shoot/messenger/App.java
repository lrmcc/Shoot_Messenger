/*
  Shoot Messenger
  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import java.util.Scanner; 
public class App {
    public String getGreeting() {
        return "Welcome to Shoot Messenger!";
    }

    public static String[] getUserInputStr() {
        String[] varNames = {"username", "mode"};
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String[] userInputArr = new String[2];
        userInputArr[0] = null;
        userInputArr[1] = null;
        for (int i = 0; i < varNames.length; i++) {
            System.out.println("Enter " + varNames[i] + ": ");
            while(userInputArr[i] == null){
                if (scanner.hasNextLine()){
                    userInputArr[i] = scanner.nextLine();
                }
            }
        } 
        scanner.close();
        return userInputArr;
      }

    public static void main(String[] args) {
        App messenger = new App();
        System.out.println(messenger.getGreeting());
        String[] userInputArr = getUserInputStr();
        String username =  userInputArr[0];
        String mode =  userInputArr[1];
        String hostname = "Shoot";
        int port = 5050;
        System.out.println(username + " selected " + mode + " mode!");
        if (mode.equals("server")) {
            ShootServer shootServer = new ShootServer(hostname, port);
            shootServer.start();
        }
        if (mode.equals("client")) {
            ShootClient shootClient = new ShootClient(hostname, port);
            shootClient.start();
        }
    }
}
