public class PasswordStrengthChecker {
    public static int oldHashCode(String s, int M) {
        int hash = 0;
        int skip = Math.max(1, s.length() / 8);
        for (int i = 0; i < s.length(); i += skip) {
            hash = (hash * 37 + s.charAt(i)) % M;
        }
        return Math.abs(hash);
    }

    public static int newHashCode(String s, int M) {
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = (hash * 31 + s.charAt(i)) % M;
        }
        return Math.abs(hash);
    }
}