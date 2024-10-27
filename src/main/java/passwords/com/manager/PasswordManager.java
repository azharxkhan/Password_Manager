package passwords.com.manager;

import java.util.HashMap;
import java.util.Map;

import passwords.com.Generator.PasswordGenerator;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

/**
 * PasswordManager is a web service that allows users to store and retrieve 
 * generated passwords for various services. It uses Spark to create HTTP 
 * endpoints for storing and retrieving passwords by service name.
 */
public class PasswordManager {
    private Map<String, String> passwordStore = new HashMap<>();

    /**
     * Initializes the PasswordManager and configures the web service on port 4567.
     * Sets up two endpoints: one for storing a generated password and another for 
     * retrieving a password by service name.
     */
    public PasswordManager() {
        port(4567);
        
        post("/storePassword/:service", (req, res) -> {
            String service = req.params(":service");
            String password = new PasswordGenerator().generatePassword();
            storePassword(service, password);
            return password; 
        });

        get("/getPassword/:service", (req, res) -> {
            String service = req.params(":service");
            return getPassword(service);
        });
    }

    /**
     * Stores the specified password associated with the given service name.
     *
     * @param service The name of the service to associate with the password.
     * @param password The password to store for the specified service.
     */
    public void storePassword(String service, String password) {
        passwordStore.put(service, password);
    }

    /**
     * Retrieves the password associated with the given service name.
     *
     * @param service The name of the service for which to retrieve the password.
     * @return The password associated with the specified service, or null if none exists.
     */
    public String getPassword(String service) {
        return passwordStore.get(service);
    }
}
