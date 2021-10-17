/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bachkasika.trie;

import bachkasika.domain.Note;
import java.util.ArrayList;
import java.util.Random;

/**
 * Tämä luokka vastaa Markovin ketjujen luomisesta. Sille annetaan luodessa
 * parametriksi Trie-olio, jonka pohjalta ketju luodaan.
 * 
 * @author hede
 */
public class MarkovChain {
    
    private Trie trie;
    private ArrayList<Note> noteList;
    
    public MarkovChain(Trie trie) {
        this.trie = trie;
        this.noteList = new ArrayList<>();
    }
    
    /**
     * Muokkaa sävelkorkeustaulukkoa listaksi, johon Triessä voidaan syöttää 
     * nuottien kestot ja viipeet.
     * @param keyChain sävelkorkeustaulukko
     * @return valmis nuottitaulukko
     */
    public ArrayList<Note> createNoteListFromKeyChain(int[] keyChain) {
        for (int i = 0; i < keyChain.length - this.trie.getChainLength(); i++) {
            ArrayList<Note> helperList = new ArrayList<>();
            for (int j = 0; j < this.trie.getChainLength(); j++) {
                helperList.add(this.noteList.get(i + j));
            }
            helperList = this.trie.getFramedKeySequence(this.noteList);
            this.noteList.add(i + this.trie.getChainLength(), helperList.get(helperList.size() - 1));
        }
        return this.noteList;
    }
    
    /**
     * Metodi rakentaa sävelkorkeuksista Markovin ketjun. Ei sisällä nuottien
     * pituuksia, ainoastaan järjestyksen.
     * @param notes nuottien määrä
     * @return nuottien korkeudet taulukossa
     */
    public int[] createKeyChain(int notes) {
        int[] chain = new int[notes];
        int[] firstNotes = this.trie.getRandomSequence();
        int[] helperChain = new int[firstNotes.length];
        for (int i = 0; i < firstNotes.length; i++) {
            chain[i] = firstNotes[i];
            Note n = new Note();
            n.setKey(firstNotes[i]);
            this.noteList.add(n);
            if (i >= 1) {
                helperChain[i - 1] = firstNotes[i];
            }
            this.trie.getFramedKeySequence(this.noteList);
        }
        for (int i = firstNotes.length; i < chain.length; i++) {
            helperChain = trie.findAndFill(helperChain);
            chain[i] = helperChain[helperChain.length - 1];
            Note n = new Note();
            n.setKey(chain[i]);
            this.noteList.add(n);
            for (int j = 0; j < helperChain.length - 1; j++) {
                helperChain[j] = helperChain[j + 1];
            }
            helperChain[helperChain.length - 1] = -1;
        }
        return chain;
    }
    
    
    
    

}
