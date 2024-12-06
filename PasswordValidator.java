import java.util.List;

public class PasswordValidator {
    public static boolean isStrongPassword(String password, List<String> dictionary,
                                           HashTableChaining hashTable, String hashType) {
        if (password.length() < 8) return false;

        if (hashTable.search(password, hashType)) return false;

        for (String word : dictionary) {
            if (password.startsWith(word) && password.substring(word.length()).matches("\\d")) {
                return false;
            }
        }
        return true;
    }
}