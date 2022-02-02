/*
  Lukes-Mac-mini.local
  java EchoServer 7
  java EchoClient echoserver.example.com 7
*/
package com.lukemcconnell.shoot.messenger;

public class App {
    public String getGreeting() {
        return "Welcome to Shoot Messenger!";
    }

    public static void main(String[] args) {
        App messenger = new App();
        System.out.println(messenger.getGreeting());
    }
}
