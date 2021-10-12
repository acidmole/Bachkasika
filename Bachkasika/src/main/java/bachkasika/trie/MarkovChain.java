/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bachkasika.trie;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author hede
 */
public class MarkovChain {
    
    private Trie trie;
    
    public MarkovChain(Trie trie) {
        this.trie = trie;
    }
    
    public int[] createChain(int notes) {
        int[] chain = new int[notes];
        int[] firstNotes = this.trie.getRandomSequence();
        int[] helperChain = new int[firstNotes.length];
        for (int i = 0; i < firstNotes.length; i++) {
            chain[i] = firstNotes[i];
            if (i >= 1) {
                helperChain[i - 1] = firstNotes[i];
            }
        }
        for (int i = firstNotes.length; i < chain.length; i++) {
            helperChain = trie.findAndFill(helperChain);
            chain[i] = helperChain[helperChain.length - 1];
            for (int j = 0; j < helperChain.length - 1; j++) {
                helperChain[j] = helperChain[j + 1];
            }
            helperChain[helperChain.length - 1] = -1;
        }
        return chain;
    }
    
    

}
