/*
*  Shoot Client 0.0.1
*  Luke McConnell
*/

package com.lukemcconnell.shoot.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class App {

    /**
     * Entry point to Shoot Messenger client application.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

        System.out.println("\nWelcome to Shoot Messenger!\n");
        try {
            Client Client = new Client();
            Client.start();
        } catch (Exception e) {
            System.err.println("Error:" + e);
            System.exit(-1);
        }
    }

    @GetMapping("/shoot")
    public String messenger() {
        return "<h1>Shoot Messenger</h1><h3>Client App<h/3>:";
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> containerCustomizer() {
        return factory -> factory.setPort(9021);
    }
}