/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bachkasika.trie;

import bachkasika.domain.Note;
import java.util.ArrayList;
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
    
    public Trie() {
        
    }
    
    public void insertMelodyFromNoteList(ArrayList<Note> noteList) {
        
        
        for(Note n : noteList) {
            
        }
        
    }
    
    private ArrayList<Note> parseMelodyFromNoteList(ArrayList<Note> noteList) {
        
        ArrayList<Note> helperList = new ArrayList<>();
        ArrayList<Note> returnList = new ArrayList<>();
        long comparedTick = 0;
        Iterator<Note> iter = noteList.iterator();
        while (iter.hasNext()) {
            Note n = iter.next();
            if (n.getTick() > comparedTick) {
                Note highestNote = Collections.max(helperList);
                returnList.add(highestNote);
                helperList.clear();
            }
            helperList.add(n);
        }
       Note highestNote = Collections.max(helperList);
       returnList.add(highestNote);

   }

}
