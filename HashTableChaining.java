import java.util.LinkedList;

public class HashTableChaining {
    private final LinkedList<String>[] table;
    private final int M;
    private final HashFunction hashFunction;
    private int comparisons;

    @SuppressWarnings("unchecked")
    public HashTableChaining(int M, HashFunction hashFunction) {
        this.M = M;
        this.hashFunction = hashFunction;
        this.table = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public void insert(String key) {
        int index = hashFunction.hash(key, M);
        table[index].add(key);
    }

    public boolean search(String key) {
        int index = hashFunction.hash(key, M);
        comparisons = 0;
        for (String word : table[index]) {
            comparisons++;
            if (word.equals(key)) return true;
        }
        return false;
    }

    public int getComparisons() {
        return comparisons;
    }
}