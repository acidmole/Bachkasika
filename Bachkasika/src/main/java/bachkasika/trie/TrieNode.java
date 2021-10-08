package bachkasika.trie;

import java.util.ArrayDeque;
import java.util.Random;

/**
 * Solmusta vastaava luokka Trie-rakenteessa
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
    
    /**
     * Lisää solmulle lapsen.
     * @param sequence kaikki lapset
     * @param level millä puun tasolla ollaan
     */
    public void addChildren(int[] sequence, int level) {
        if (level < sequence.length) {
            this.childFrequence[sequence[level]]++;
            if (this.children[sequence[level]] == null) {
                this.children[sequence[level]] = new TrieNode();
            }
            this.children[sequence[level]].addChildren(sequence, level + 1);
        }
    }
    
    /**
     *
     * @return solmun lapset
     */
    public TrieNode[] getChildren() {
        return this.children;
    }
    
    
    /**
     * Täyttää annetun kokoisen taulukon jollain ketjulla.
     * @param depth mitä kohtaa taulukossa täytetään. Kyseessä on myös Trien
     * tämänhetkinen syvyys.
     * @param sequence taulukko, johon lapset täytetään
     * @param node solmu, josta alkaen lähdetään täyttämään
     * @return täytetty taulukko
     */
    public int[] fillSequence(int depth, int[] sequence, TrieNode node) {
        if (depth == sequence.length) {
            return sequence;
        }
        int nextChild = node.randomChild();
        sequence[depth] = nextChild;
        return this.children[nextChild].fillSequence(depth + 1, sequence, node);
    }
    
    
    /**
     * Etsii ja täyttää annettuun taulukkoon tyhjät kohdat. Jos taulukossa ei
     * ole tyhjiä kohtia, palautetaan alkuperäinen taulukko.
     * 
     * Metodi etsii puusta lapset.
     * 
     * @param sequence 
     * @return täytetty taulukko
     * @see fillSequence
     */
    public int[] findAndFillBranch(int[] sequence, TrieNode node) {
        if (sequence[sequence.length-1] >= 0) {
            return sequence;
        }
        int i = 0;
        TrieNode nextChild = null;
        while (i < sequence.length && sequence[i] >= 0) {
            nextChild = node.getChildren()[sequence[i]];
            i++;
        }
        return(this.fillSequence(i, sequence, nextChild));
    }
    
    
    /**
     * Arpoo satunnaisen solmun lapsista. Ei ole vielä painotettu.
     * @return lapsen arvo children[] taulukossa
     */
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
