package com.lukemcconnell.shoot.messenger;

import java.util.Random;
import java.io.BufferedReader;
import java.net.InetAddress;

public class ShootUtils {

    public static final String hostname = "localhost";
    public static final int port = 5050;
    public static final String splitMarker = "::::";

    /**
     * Splits the string parameter 
     * @param str
     * @param idx
     * @return
     */
    public static String getStrFromSplit(String str, int idx){
        String splitStr = "";
        try{
            splitStr = str.split(splitMarker)[idx];
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return splitStr;
    }

    /**
     * Returns a 6 character string composed of 3 hex numbers followed by 3 alphabet characters
     * @return
     */
    public static String getRandomStr() {
        Random generator = new Random(System.nanoTime());
        double random1 = generator.nextDouble();
        String part1 = Integer.toHexString((int) Math.floor(random1 * (3831) + 256));
        String part2 = String.valueOf((char) (random1 * ('z' - 'a') + 'a') + (char) (random1 * ('z' - 'a') + 'a')
                + (char) (random1 * ('z' - 'a') + 'a'));
        return part1 + part2;
    }

    /**
     * Returns the local host computer name
     * @return
     */
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

    /**
     * Takes in an variable name (user to be prompted for input for varName) and an input stream
     * Returns String value meant to represent varName's value
     * @param varName
     * @param in
     * @return
     */
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
