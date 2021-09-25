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
        for (int i=0; i<127; i++) {
            testNotes.add(new Note(0, i, 100, 100));
        }
    }
 
    @Test
    public void addingNotesSeparatesThemCorrectly() {
        for (Note n : testNotes) {
            trie.addNote(n);
        }
        for (int i = 0; i<127; i++) {
            assertEquals(1, this.trie.getNodesByKey(i).size());
        }
    }
    
    @Test
    public void trieDoesNotAddDuplicates() {
        Note n = new Note(0, 60, 100, 100);
        trie.addNote(n);
        trie.addNote(n);
        trie.addNote(n);
        assertEquals(1, this.trie.getNodesByKey(60).size());
        
    }
    
    @Test
    public void childrenAreLinkedCorrectly() {
        NoteNode nn1 = new NoteNode(testNotes.get(60));
        NoteNode nn2 = new NoteNode(testNotes.get(60));
        NoteNode nn3 = new NoteNode(testNotes.get(61));
        
        trie.addNote(nn1);
        trie.addNote(nn2);
        trie.addNote(nn1);
        trie.addNote(nn1);
        trie.addNote(nn3);
        trie.addNote(nn1);
        
        assertEquals(1, nn3.getChildren().size());
        assertEquals(1, nn2.getChildren().size());
        assertEquals(3, nn2.getChildren().size());
        
        
    }
}