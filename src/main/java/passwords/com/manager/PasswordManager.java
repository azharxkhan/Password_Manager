package passwords.com.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import passwords.com.Generator.PasswordGenerator;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

/**
 * PasswordManager is a web service that allows users to store and retrieve
 * hashed passwords for various services. Passwords and their service links
 * are stored securely in a CSV file.
 */
public class PasswordManager {
    private static final String CSV_FILE = "passwords.csv";
    private Map<String, String> passwordStore = new HashMap<>();

    public PasswordManager() {
        port(4567);
        
        post("/storePassword/:service", (req, res) -> {
            String service = req.params(":service");
            String password = new PasswordGenerator().generatePassword();
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            
            storePassword(service, hashedPassword);
            writePasswordToCSV(service, hashedPassword);
            
            return "Generated and stored password: " + password; 
        });

        get("/getPassword/:service", (req, res) -> {
            String service = req.params(":service");
            String storedHash = getPassword(service);
            return storedHash != null ? "Stored hashed password: " + storedHash : "No password found for service: " + service;
        });
    }

    public void storePassword(String service, String hashedPassword) {
        passwordStore.put(service, hashedPassword);
    }

    public String getPassword(String service) {
        if (passwordStore.isEmpty()) {
            loadPasswordsFromCSV();
        }
        return passwordStore.get(service);
    }

    private void writePasswordToCSV(String service, String hashedPassword) {
        try (FileWriter writer = new FileWriter(CSV_FILE, true)) {
            writer.append(service).append(",").append(hashedPassword).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPasswordsFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    passwordStore.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
