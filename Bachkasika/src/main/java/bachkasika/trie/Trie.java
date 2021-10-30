/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bachkasika.trie;

import bachkasika.domain.Note;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author hede
 * 
 * Luokka tallentaa chainLength-muuttujan pituisia sävelketjuja. Viitteet
 * tallennetaan @see TrieNode olioina.
 */
public class Trie {
    
    private final int chainLength;
    private TrieNode root;
    private FrameNode frameRoot;
    private int chains;
    
    
    /**
     * Luo kaksi Trie-oliota. Toinen sisältää nuottien korkeudet ja toinen
     * nuottien pituudet ja seuraavan nuotin soimisajan. 
     * Marklovin ketju rakennetaan syöttämällä ensimmäinen jälkimmäiseen.
     * @see MarkovChain
     * 
     * @param chainLength luotavien puun juurien pituus
     */
    public Trie(int chainLength, int bassNoteBoundary) {
        
        this.chainLength = chainLength;
        this.root = new TrieNode();
        this.frameRoot = new FrameNode(bassNoteBoundary, 0, 0);
    }
    
    /**
     * Metodin tehtävänä on rakentaa molemmat triet chainLength-pituisina
     * sävelkulkuina.
     * 
     * @param noteList sävelkorkeudet Note-olioiden listana
     */
    public int[][] insertFromNoteList(ArrayList<Note> noteList) {
        
        this.buildFrameTrie(noteList);
        return this.insertSequences(noteList);
    }
    
    /**
     * Rakentaa nuottien pituuksien ja seuraavan nuotin alkamisen määräävän
     * kehikko-trien.
     * @param noteSequence Lista, jonka mukaan trie rakennetaan.
     */
    public void buildFrameTrie(ArrayList<Note> noteSequence) {
        for (int i = 0; i < noteSequence.size() - this.chainLength; i++) {
            FrameNode node = this.frameRoot;
            for (int j = 0; j < this.chainLength - 1; j++) {
                Note n = noteSequence.get(i + j);
                node = node.addChild(n.getKey(), n.getDuration(), n.getDelay());
            }
        }
    }
    
    public ArrayList<Note> getFramedKeySequence(int[] keyChain) {        
        return this.frameRoot.fitKeysToFrame(keyChain, this.chainLength);
    }
    
    /**
     * Metodi, joka etsii rakenteesta jatkumon annetulle sekvenssille.
     * @param sequence annettu sekvenssi
     * @param amount täytettävien kohtien määrä
     * @return täydennetty sekvenssi. Virheellisillä syötteillä tyhjä taulu.
     */
    public int[] fill(int[] sequence, int amount) {
        if (amount > sequence.length) {
            return new int[amount];
        }
        return this.root.fillSequence(sequence.length - amount, sequence, this.root);
    }
    
    
    /**
     * Metodi, joka etsii ja täydentää tyhjät kohdat (arvo <= 0) taulukossa.
     * @param sequence täydennettävä taulukko
     * @return täydennetty taulukko
     */
    public int[] findAndFill(int[] sequence) {
        return this.root.findAndFillBranch(sequence);
    }
    
    
    /**
     * Kokonaan uuden ketjun generointia toteuttava metodi.
     * 
     * @return satunnainen, chainLength-pituinen ketju Triestä.
     */
    public int[] getRandomSequence() {
        return this.root.fillSequence(0, new int[this.chainLength], this.root);
    }
    
    /**
     * Katkoo annetun listan chainLength*n -pituisiksi sekvensseiksi, jotka 
     * voidaan ajaa Triehen.
     * @param filteredNoteList vain yksittäisiä nuotteja kerrallaan sisältävä lista
     * @return sekvenssit sisältävä taulukko
     */
    public int[][] insertSequences(ArrayList<Note> filteredNoteList) {
        int[][] sequences = new int[this.chainLength * filteredNoteList.size() + 1][this.chainLength];
        for (int i = 0; i < (filteredNoteList.size() - this.chainLength); i++) {
            int[] readySequence = new int[this.chainLength];
            for (int j = 0; j < this.chainLength; j++) {
                sequences[i][j] = filteredNoteList.get(i + j).getKey();
                readySequence[j] = sequences[i][j];
            }
            this.insert(readySequence);
        }
        return sequences;
    }
    
     /**
     * Syöttää tietorakenteeseen annetun ketjun.
     * @param sequence 
     */
    private void insert(int[] sequence) {
        this.chains++;
        this.root.addChildren(sequence, 0);
    }
    
    public TrieNode getRoot() {
        return this.root;
    }
    
    public int getChainLength() {
        return this.chainLength;
    }
    
    public int getChains() {
        return this.chains;
    }


}
