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
 * Luokka on verkko, joka tallentaa sävelkorkeudeltaan erilaisia nuotteja.
 * Sävelkorkeudeltaan samanlaiset nuotit voivat olla erilaisia kestoltaan ja
 * päällekkäisyydeltään, joten saman korkeuden sisältävät nuotit ovat
 * talletettu samaan listaan. Tallennettaessa vertaillaan,  jos täsmälleen 
 * samanlainen nuotti on jo rakenteessa. 
 * 
 * Jokaisella verkon erilaisella nuotilla on talletettu myös verkon
 * naapurisolmut, joihin voidaan siirtyä.
 */
public class Trie {
    
    private final int chainLength;
    private TrieNode root;
    
    public Trie(int chainLength) {
        
        this.chainLength = chainLength;
        this.root = new TrieNode();
    }
    
    public void insertFromNoteList(ArrayList<Note> noteList) {
        
        noteList = this.filterHighNotesFromList(noteList);
        int[][] sequences = this.trimAndInsertSequences(noteList);
    }
    
    private void insert(int[] sequence) {
        this.root.addChildren(sequence, 0);
    }
    
    public int[] getRandomSequence() {
        return this.root.fillSequence(0, new int[this.chainLength]);
    }
    
    private ArrayList<Note> filterHighNotesFromList(ArrayList<Note> noteList) {
        
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
       Note highestNote = Collections.max(helperList);
       finalList.add(highestNote);
       
       return finalList;
    }
    
    private int[][] trimAndInsertSequences(ArrayList<Note> filteredNoteList) {
        int[][] sequences = new int[this.chainLength * filteredNoteList.size() +1][this.chainLength];
        
        System.out.println(filteredNoteList.size());
        for (int i = 0; i < (filteredNoteList.size() - this.chainLength); i++) {
            int[] readySequence = new int[this.chainLength];
            for (int j = 0; j < this.chainLength; j++) {
                sequences[i][j] = filteredNoteList.get(i+j).getKey();
                readySequence[j] = sequences[i][j];
            }
            this.insert(readySequence);
        }
        return sequences;
    }

}
