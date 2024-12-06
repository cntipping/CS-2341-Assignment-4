import java.io.*;
import java.net.*;
import java.util.*;

public class PasswordStrengthChecker {
    public static List<String> loadDictionary(String url) throws IOException {
        List<String> dictionary = new ArrayList<>();
        URL dictionaryUrl = new URL(url);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(dictionaryUrl.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line.trim());
            }
        }
        return dictionary;
    }
}