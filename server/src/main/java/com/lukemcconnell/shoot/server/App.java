/*
*  Shoot Server 0.0.1
*  Luke McConnell
*/

package com.lukemcconnell.shoot.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;

@SpringBootApplication
@RestController
public class App {

    /**
     * Entry point to Shoot Messenger server application.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("\nLaunching Shoot Messenger server.\n");
        SpringApplication.run(App.class, args);

        try {
            Server Server = new Server();
            Server.start();
        } catch (Exception e) {
            System.err.println("Error:" + e);
            System.exit(-1);
        }
    }

    @GetMapping("/status")
    public String status() {
        return "<h1>Shoot Messenger</h1><h3>Connectected Users<h/3>:";
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> containerCustomizer() {
        return factory -> factory.setPort(9020);
    }

}