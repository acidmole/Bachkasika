/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bachkasika.trie;

import bachkasika.domain.Note;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

/**
 * @author hede
 * 
 * Luokka tallentaa chainLength-muuttujan pituisia sävelketjuja. Viitteet
 * tallennetaan @see TrieNode olioina.
 */
public class Trie {
    
    private final int chainLength;
    private TrieNode root;
    
    /**
     *
     * @param chainLength luotavien puun juurien pituus
     */
    public Trie(int chainLength) {
        
        this.chainLength = chainLength;
        this.root = new TrieNode();
    }
    
    /**
     * Tämä luokka tekee chainLengthin pituisia sävelkulkuja, jotka talletetaan
     * Triehen.
     * 
     * @param noteList sävelkorkeudet Note-olioina
     */
    public int[][] insertFromNoteList(ArrayList<Note> noteList) {
        
        noteList = this.filterHighNotesFromList(noteList);
        return this.trimAndInsertSequences(noteList);
    }
    
    /**
     * Syöttää tietorakenteeseen annetun ketjun.
     * @param sequence 
     */
    private void insert(int[] sequence) {
        this.root.addChildren(sequence, 0);
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
    
    public TrieNode getRoot() {
        return this.root;
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
     * Metodi, jonka tehtävä on palauttaa minkä tahansa Note-olioita
     * sisältävän listan korkeimmat soivat nuotit.
     * 
     * @param noteList Note-olion lista
     * @return korkeimmat nuotit sisältävä lista. jos ei ole tarpeeksi
     * elementtejä, palautetaan null
     */
    public ArrayList<Note> filterHighNotesFromList(ArrayList<Note> noteList) {
        if (noteList.size() < this.chainLength) return null;
        ArrayList<Note> helperList = new ArrayList<>();
        ArrayList<Note> finalList = new ArrayList<>();
        long comparedTick = 0;
        Iterator<Note> iter = noteList.iterator();
        while (iter.hasNext()) {
            Note n = iter.next();
            if (n.getTick() > comparedTick) {
                Note highestNote = Collections.max(helperList);
                finalList.add(highestNote);
                helperList.clear();
            }
            helperList.add(n);
            comparedTick = n.getTick();
        }
        System.out.println(comparedTick);
        Note highestNote = Collections.max(helperList);
        finalList.add(highestNote);
        return finalList;
    }
    
    /**
     * Katkoo annetun listan chainLength*n -pituisiksi sekvensseiksi, jotka 
     * voidaan ajaa Triehen.
     * @param filteredNoteList vain yksittäisiä nuotteja kerrallaan sisältävä lista
     * @return sekvenssit sisältävä taulukko
     */
    public int[][] trimAndInsertSequences(ArrayList<Note> filteredNoteList) {
        int[][] sequences = new int[this.chainLength * filteredNoteList.size() + 1][this.chainLength];
        System.out.println(filteredNoteList.size());
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

}
