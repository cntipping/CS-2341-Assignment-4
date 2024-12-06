import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class Main {
    private static final int M_SC = 1000;
    private static final int M_LP = 20000;
    private static final String DICTIONARY_URL = "https://www.mit.edu/~ecprice/wordlist.10000";

    public static void loadDictionary(HashTableChaining[] scTables, HashTableLinearProbing[] lpTables) throws Exception {
        URL url = new URL(DICTIONARY_URL);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String word;
            while ((word = reader.readLine()) != null) {
                word = word.trim();
                for (HashTableChaining table : scTables) {
                    table.insert(word);
                }
                for (HashTableLinearProbing table : lpTables) {
                    table.insert(word);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        HashTableChaining scOldHash = new HashTableChaining(M_SC, PasswordStrengthChecker::oldHashCode);
        HashTableChaining scNewHash = new HashTableChaining(M_SC, PasswordStrengthChecker::newHashCode);
        HashTableLinearProbing lpOldHash = new HashTableLinearProbing(M_LP, PasswordStrengthChecker::oldHashCode);
        HashTableLinearProbing lpNewHash = new HashTableLinearProbing(M_LP, PasswordStrengthChecker::newHashCode);

        HashTableChaining[] scTables = {scOldHash, scNewHash};
        HashTableLinearProbing[] lpTables = {lpOldHash, lpNewHash};

        System.out.println("Downloading dictionary...");
        loadDictionary(scTables, lpTables);
        System.out.println("Dictionary loaded successfully.");

        String[] passwords = {
                "account8",
                "accountability",
                "9a$D#qW7!uX&Lv3zT",
                "B@k45*W!c$Y7#zR9P",
                "X$8vQ!mW#3Dz&Yr4K5"
        };

        for (String password : passwords) {
            System.out.println("Password: " + password);
            for (int i = 0; i < scTables.length; i++) {
                String result = PasswordValidator.validatePassword(password, new HashTableChaining[]{scTables[i]}, new HashTableLinearProbing[]{lpTables[i]});
                System.out.println("  " + (i == 0 ? "Old" : "New") + " Hash SC: " + result + ", Comparisons: " + scTables[i].getComparisons());
                System.out.println("  " + (i == 0 ? "Old" : "New") + " Hash LP: " + result + ", Comparisons: " + lpTables[i].getComparisons());
            }
            System.out.println();
        }
    }
}