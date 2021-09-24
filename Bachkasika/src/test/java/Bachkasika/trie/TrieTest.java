/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Bachkasika.trie;

import bachkasika.domain.Note;
import bachkasika.io.BachkasikaFileService;
import bachkasika.midi.MIDIParser;
import bachkasika.trie.NoteNode;
import bachkasika.trie.Trie;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hede
 */
public class TrieTest {
    
    Trie trie;
    ArrayList<Note> testNotes;
    
    public TrieTest() {
    }
    
    @Before
    public void setUp() {
        trie = new Trie();
        testNotes = new ArrayList<>();
        for (int i=60; i<63; i++) {
            testNotes.add(new Note(0, i, 100, 100));
        }
    }
 
    @Test
    public void noteAddingWorksRight() {
        for (Note n : testNotes) {
            trie.addNote(n);
        }
        assertEquals(1, this.trie.getNodesByKey(60).size());
        assertEquals(1, this.trie.getNodesByKey(61).size());
        assertEquals(1, this.trie.getNodesByKey(62).size());
        
    }
    
    @Test
    public void trieDoesNotAddDuplicates() {
        Note n = new Note(0, 60, 100, 100);
        trie.addNote(n);
        trie.addNote(n);
        trie.addNote(n);
        assertEquals(1, this.trie.getNodesByKey(60).size());
        
    }
}