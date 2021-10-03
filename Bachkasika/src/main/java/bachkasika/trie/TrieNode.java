package bachkasika.trie;

import java.util.ArrayDeque;
import java.util.Random;

/**
 *
 * @author hede
 */
public class TrieNode {
    
    private TrieNode[] children;
    private int key;
    private int[] childFrequence;
    
    public TrieNode() {
        this.children = new TrieNode[128];
        this.childFrequence = new int[128];
    }
    
    public void addChildren(int[] sequence, int level) {
        if (level < sequence.length) {
            this.childFrequence[sequence[level]]++;
            if (this.children[sequence[level]] == null) {
                this.children[sequence[level]] = new TrieNode();
            }
            this.children[sequence[level]].addChildren(sequence, level + 1);
        }
    }
    
    public TrieNode[] getChildren() {
        return this.children;
    }
    
    public int[] fillSequence(int depthRemaining, int[] sequence) {
        if (depthRemaining == sequence.length) {
            return sequence;
        }
        int nextChild = this.randomChild();
        sequence[depthRemaining] = nextChild;
        return this.children[nextChild].fillSequence(depthRemaining + 1, sequence);
    }
    
    private int randomChild() {
        Random rn = new Random();
        int child;
        while (true) {
            child = 40 + rn.nextInt(60);
            if (this.children[child] != null) {
                return child;
            }
        }
    }
    
    @Override
    public String toString() {
        String concat = "";
        for (int i = 0; i < 128; i++) {
            if (this.children[i] != null) {
                concat += ("Lapsi: " + i + ", frekvenssi: " + this.childFrequence[i] + "\n");
            }
        }
        return concat;
    }
}
