import org.junit.After;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

import passwords.com.manager.PasswordManager;
import static spark.Spark.stop;

public class PasswordManagerTest {
    private PasswordManager passwordManager;

    @Before
    public void setUp() {
        passwordManager = new PasswordManager();
    }

    @After
    public void tearDown() {
        stop(); 
    }

    @Test
    public void testRetrieveNonExistingPassword() {
        String serviceName = "google.com";
        
        String retrievedPassword = passwordManager.getPassword(serviceName);
        
        assertNull("The retrieved password should be null for non-existing password.", 
                   retrievedPassword);
    }
}
