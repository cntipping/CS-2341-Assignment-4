import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class HashTableChaining {
    private List<List<Entry>> table;
    private int size;

    public HashTableChaining(int size) {
        this.size = size;
        this.table = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            table.add(new ArrayList<>());
        }
    }

    private int hash(String word, String type) {
        int hash = 0;
        if (type.equals("old")) {
            int skip = Math.max(1, word.length() / 8);
            for (int i = 0; i < word.length(); i += skip) {
                hash = (hash * 37) + word.charAt(i);
            }
        } else if (type.equals("new")) {
            for (int i = 0; i < word.length(); i++) {
                hash = (hash * 31) + word.charAt(i);
            }
        }
        return Math.abs(hash % size);
    }

    public void insert(String word, int lineNumber, String type) {
        int index = hash(word, type);
        table.get(index).add(new Entry(word, lineNumber));
    }

    public boolean search(String word, String type) {
        int index = hash(word, type);
        for (Entry entry : table.get(index)) {
            if (entry.word.equals(word)) {
                return true;
            }
        }
        return false;
    }

    static class Entry {
        String word;
        int lineNumber;

        public Entry(String word, int lineNumber) {
            this.word = word;
            this.lineNumber = lineNumber;
        }
    }
}