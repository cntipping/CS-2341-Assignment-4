public class PasswordValidator {
    public static String validatePassword(String password, HashTableChaining[] scTables, HashTableLinearProbing[] lpTables) {
        if (password.length() < 8) return "Weak (too short)";

        for (HashTableChaining table : scTables) {
            if (table.search(password)) return "Weak (dictionary word)";
            if (password.matches("^[a-zA-Z]+\\d$") && table.search(password.substring(0, password.length() - 1))) {
                return "Weak (dictionary word + digit)";
            }
        }

        for (HashTableLinearProbing table : lpTables) {
            if (table.search(password)) return "Weak (dictionary word)";
            if (password.matches("^[a-zA-Z]+\\d$") && table.search(password.substring(0, password.length() - 1))) {
                return "Weak (dictionary word + digit)";
            }
        }

        return "Strong";
    }
}