package passwords.com.Generator;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();

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
