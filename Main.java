import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            String dictionaryUrl = "https://www.mit.edu/~ecprice/wordlist.10000";

            // Load the dictionary from the URL
            List<String> dictionary = PasswordStrengthChecker.loadDictionary(dictionaryUrl);
            System.out.println("Dictionary loaded successfully.");

            // Initialize hash tables
            HashTableChaining chainingTable = new HashTableChaining(1000);
            HashTableLinearProbing linearTable = new HashTableLinearProbing(20000);

            // Populate hash tables with dictionary words
            for (int i = 0; i < dictionary.size(); i++) {
                String word = dictionary.get(i);
                chainingTable.insert(word, i + 1, "old");
                linearTable.insert(word, i + 1, "old");
            }

            System.out.println("Hash tables initialized.");

            // Interactive user input
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("\nEnter a password to test (or type 'exit' to quit): ");
                String password = scanner.nextLine();

                if (password.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting program. Goodbye!");
                    break;
                }

                // Test the password using both hash functions and both methods
                boolean resultOldChaining = PasswordValidator.isStrongPassword(password, dictionary, chainingTable, "old");
                boolean resultNewChaining = PasswordValidator.isStrongPassword(password, dictionary, chainingTable, "new");

                // Output results
                System.out.println("Password: " + password);
                System.out.println("  Old Hash (Chaining): " + (resultOldChaining ? "Strong" : "Weak"));
                System.out.println("  New Hash (Chaining): " + (resultNewChaining ? "Strong" : "Weak"));

                // Optionally test linear probing as well
                boolean resultOldLinear = PasswordValidator.isStrongPassword(password, dictionary, chainingTable, "old");
                boolean resultNewLinear = PasswordValidator.isStrongPassword(password, dictionary, chainingTable, "new");

                System.out.println("  Old Hash (Linear Probing): " + (resultOldLinear ? "Strong" : "Weak"));
                System.out.println("  New Hash (Linear Probing): " + (resultNewLinear ? "Strong" : "Weak"));
            }

            scanner.close();
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}