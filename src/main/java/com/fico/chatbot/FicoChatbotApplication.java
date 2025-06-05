package com.fico.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for FICO Chatbot
 * @SpringBootApplication tells Spring this is the main configuration class
 */
@SpringBootApplication
public class FicoChatbotApplication {
    
    /**
     * Main method - the starting point of our application
     * When you run this, it starts the web server
     */
    public static void main(String[] args) {
        SpringApplication.run(FicoChatbotApplication.class, args);
        System.out.println("🚀 FICO Chatbot Backend Started Successfully!");
        System.out.println("📡 API Available at: http://localhost:8080/api");
    }
}