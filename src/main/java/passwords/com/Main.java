package passwords.com;

import java.util.Scanner;

import passwords.com.Generator.PasswordGenerator;
import passwords.com.manager.PasswordManager;

public class Main {
    
    public static void main(String[] args) {
        PasswordManager manager = new PasswordManager(); 
        PasswordGenerator generator = new PasswordGenerator();

        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Password Manager is running. Type 'exit' to stop the server.");

        while (true) {
            System.out.print("Enter command (generate/retrieve/exit): ");
            command = scanner.nextLine();

            if ("exit".equalsIgnoreCase(command)) {
                System.out.println("Stopping the server...");
                break; 
            } else if ("generate".equalsIgnoreCase(command)) {
                System.out.print("Enter service name: ");
                String service = scanner.nextLine();
                String generatedPassword = generator.generatePassword();
                manager.storePassword(service, generatedPassword);
                System.out.println("Generated Password for " + service + ": " + generatedPassword);
            } else if ("retrieve".equalsIgnoreCase(command)) {
                System.out.print("Enter service name: ");
                String service = scanner.nextLine();
                String retrievedPassword = manager.getPassword(service);
                if (retrievedPassword != null) {
                    System.out.println("Retrieved Password for " + service + ": " + retrievedPassword);
                } else {
                    System.out.println("No password found for " + service);
                }
            } else {
                System.out.println("Unknown command. Please enter 'generate', 'retrieve', or 'exit'.");
            }
        }

        scanner.close(); 
        System.out.println("Server stopped.");
        System.exit(0);
    }
}