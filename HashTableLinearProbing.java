public class HashTableLinearProbing {
    private final String[] table;
    private final int M;
    private final HashFunction hashFunction;
    private int comparisons;

    public HashTableLinearProbing(int M, HashFunction hashFunction) {
        this.M = M;
        this.hashFunction = hashFunction;
        this.table = new String[M];
    }

    public void insert(String key) {
        int index = hashFunction.hash(key, M);
        while (table[index] != null) {
            index = (index + 1) % M;
        }
        table[index] = key;
    }

    public boolean search(String key) {
        int index = hashFunction.hash(key, M);
        comparisons = 0;
        while (table[index] != null) {
            comparisons++;
            if (table[index].equals(key)) return true;
            index = (index + 1) % M;
        }
        return false;
    }

    public int getComparisons() {
        return comparisons;
    }
}