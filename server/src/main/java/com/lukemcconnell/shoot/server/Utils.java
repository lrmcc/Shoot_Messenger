package com.lukemcconnell.shoot.server;

import java.util.Random;
import java.io.BufferedReader;
import java.net.InetAddress;

class Utils {

    private static Utils Utils = null;
    static final String HOSTNAME = "localhost"; // update strings with builder or format i.e. "Hello, %s!"; String.format(template, name);
    static final int PORT = 5050;
    static final String SPLITMARKER = "::::";
    static final String CONNECTION_INFO = Utils.getLocalHostName() + " connected to " + Utils.HOSTNAME + " on port " + Utils.PORT;

    
    /**
     * Private Utils constructor for singleton implementation.
     */
    private Utils(){}
    
    /**
     * Returns singleton instance of Utils.
     * @return
     */
    static Utils getInstance() {
        if (Utils == null)
            Utils = new Utils();
        return Utils;
    }
    
    /**
     * Splits the string parameter.
     * 
     * @param str
     * @param idx
     * @return
     */
    static String getStrFromSplit(String str, int idx) {
        System.out.println("Attempting to split: " + str + " @ index: " + idx);
        String splitStr = "";
        try {
            splitStr = str.split(SPLITMARKER)[idx];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Coudln't split: " + str + " @ index: " + idx);
            e.printStackTrace();
        }
        return splitStr;
    }

    /**
     * Returns a 6 character string composed of 3 hex numbers followed by 3 alphabet
     * characters.
     * 
     * @return
     */
    static String getRandomStr() {
        Random generator = new Random(System.nanoTime());
        double random1 = generator.nextDouble();
        String part1 = Integer.toHexString((int) Math.floor(random1 * (3831) + 256));
        String part2 = String.valueOf((char) (random1 * ('z' - 'a') + 'a') + (char) (random1 * ('z' - 'a') + 'a')
                + (char) (random1 * ('z' - 'a') + 'a'));
        return part1 + part2;
    }

    /**
     * Returns the local host computer name.
     * 
     * @return
     */
    static String getLocalHostName() {
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
     * Presents prompt to user and returns String value of user input.
     * 
     * @param prompt
     * @param in
     * @return
     */
    static String getInput(String prompt, BufferedReader in) {
        System.out.println(prompt);
        return getInput(in);
    }

    /**
     * Returns String value of user input.
     * 
     * @param in
     * @return
     */
    static String getInput(BufferedReader in) {
        String input = "";
        try {
            while ((input = in.readLine()).length() == 0) {
                if (input.length() != 0) {
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
