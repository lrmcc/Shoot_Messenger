package com.lukemcconnell.shoot.messenger;

import java.util.Random;
import java.io.BufferedReader;
import java.net.InetAddress;

public class ShootUtils {

    public static String getRandomStr() {
        Random generator = new Random(System.nanoTime());
        double random1 = generator.nextDouble();
        String part1 = Integer.toHexString((int) Math.floor(random1 * (3831) + 256));
        String part2 = String.valueOf((char) (random1 * ('z' - 'a') + 'a') + (char) (random1 * ('z' - 'a') + 'a')
                + (char) (random1 * ('z' - 'a') + 'a'));
        return part1 + part2;
    }

    static String getHostName() {
        String localHostName = "";
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            localHostName = localHost.getHostName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localHostName;
    }

    public static String getInput(String varName, BufferedReader in) {
        String input = "";
        try {
            System.out.println("Enter " + varName + ": ");
            while ((input = in.readLine()).length() == 0) { 
                if (input != null || input.length() != 0) {
                    System.out.println("scanner.hasNextLine()!!!");
                    input = in.readLine();
                    System.out.println("input: " + input);
                }
            }
        } catch (Exception e) {
            return "IO exeception: " + e;
        }
        return input;
    }
}
