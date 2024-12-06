class HashTableLinearProbing {
    private Entry[] table;
    private int size;

    public HashTableLinearProbing(int size) {
        this.size = size;
        this.table = new Entry[size];
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
        while (table[index] != null) {
            index = (index + 1) % size;
        }
        table[index] = new Entry(word, lineNumber);
    }

    public boolean search(String word, String type) {
        int index = hash(word, type);
        int startIndex = index;
        while (table[index] != null) {
            if (table[index].word.equals(word)) {
                return true;
            }
            index = (index + 1) % size;
            if (index == startIndex) break;
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