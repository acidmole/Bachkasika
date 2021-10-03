/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bachkasika.trie;

import java.util.Arrays;
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
        System.out.println(Arrays.toString(sequence) + ", olen tasolla " + level);
        if (level < sequence.length) {
            this.childFrequence[sequence[level]]++;
            if (this.children[sequence[level]] == null) {
                this.children[sequence[level]] = new TrieNode();
            }
            this.children[sequence[level]].addChildren(sequence, level+1);
        }
        
    }
    
    public TrieNode[] getChildren() {
        return this.children;
    }
    
    public int[] getRandomChild(int depthRemaining, int[] sequence) {
        if (depthRemaining < 1) {
            return sequence;
        }
        int nextChild = this.weighedRandomChild();
        
        return sequence;
    }
    
    private int weighedRandomChild() {
        // weighless atm
        Random rn = new Random();
        return rn.nextInt(128);
    }
    
    @Override
    public String toString() {
        String concat = "";
        for (int i=0; i<128; i++) {
            if(this.children[i] != null) {
                concat += ("Lapsi: " + i + ", frekvenssi: " + this.childFrequence[i] + "\n");
            }
        }
        return concat;
    }
    
    
}
