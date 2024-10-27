package passwords.com.Generator;

import java.security.SecureRandom;

/**
 * PasswordGenerator is responsible for generating secure passwords.
 * The generated passwords consist of random alphanumeric characters, 
 * with a mix of uppercase and lowercase letters, and are formatted with 
 * hyphens for readability.
 */
public class PasswordGenerator {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();

    /**
     * Generates a secure, random password. The password consists of three 
     * groups of five alphanumeric characters, each group separated by a hyphen.
     * Characters are randomly chosen and may be upper or lowercase.
     *
     * @return A randomly generated password string.
     */
    public String generatePassword() {
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 3; i++) { 
            for (int j = 0; j < 5; j++) {
                char character = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
                if (random.nextBoolean()) {
                    character = Character.toUpperCase(character);
                }
                password.append(character);
            }
            if (i < 2) {
                password.append("-"); 
            }
        }
        return password.toString();
    }

    public static void main(String[] args) {
        PasswordGenerator generator = new PasswordGenerator();
        String password = generator.generatePassword();
        System.out.println("Generated Password: " + password);
    }
}
