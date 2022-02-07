/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static String getGreeting() {
        return "Welcome to Shoot Messenger!";
    }

    public static ArrayList<String> getUserProfile(Scanner scanner, ArrayList<String> varNames) {
        ArrayList<String> userProfileArr = new ArrayList<String>();
        for (int i = 0; i < varNames.size(); i++) {
            String varName = varNames.get(i);
            userProfileArr.add(getUserCliInput(scanner, varName));
        }
        return userProfileArr;
    }

    public static String getUserCliInput(Scanner scanner, String varName) {
        System.out.println("Enter " + varName + ": ");
        while (true) { // what should be the while condition?
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(App.getGreeting());
        ArrayList<String> varNames = new ArrayList<String>(
                Arrays.asList("username", "mode"));
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> userProfileArr = getUserProfile(scanner, varNames);
        String username = userProfileArr.get(0);
        String mode = userProfileArr.get(1);
        String hostname = "localhost";
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
        scanner.close();
    }
}
