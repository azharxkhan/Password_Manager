package passwords.com.manager;

import java.util.HashMap;
import java.util.Map;

import passwords.com.Generator.PasswordGenerator;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

public class PasswordManager {
    private Map<String, String> passwordStore = new HashMap<>();

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

    public void storePassword(String service, String password) {
        passwordStore.put(service, password);
    }

    public String getPassword(String service) {
        return passwordStore.get(service);
    }
}
