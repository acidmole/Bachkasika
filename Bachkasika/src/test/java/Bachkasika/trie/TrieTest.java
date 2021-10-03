/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Bachkasika.trie;

import bachkasika.domain.Note;
import bachkasika.io.BachkasikaFileService;
import bachkasika.midi.MIDIParser;
import bachkasika.trie.Trie;
import bachkasika.trie.TrieNode;
import java.util.ArrayList;
import java.util.Arrays;
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
    ArrayList<Note> testSheet;
    BachkasikaFileService bsFileService;
    MIDIParser testParser;
    
    public TrieTest() {
    }
    
    @Before
    public void setUp() {
        try {
            bsFileService = new BachkasikaFileService("testMIDI.mid");
            testParser = new MIDIParser(bsFileService.getMidiFile());
            testSheet = testParser.parse(0);
        } catch (Exception e) {
            System.out.println("Virhe MIDI:ssä");
        }
    }
    
    @Test
    public void trieBuildsSequencesRight() {
        trie = new Trie(2);
        trie.insertFromNoteList(testSheet);
        assertEquals("Lapsi: 60, frekvenssi: 1\n", trie.getRoot().toString());
        TrieNode child60 = trie.getRoot().getChildren()[60];
        System.out.println(child60.toString());
        assertEquals("Lapsi: 61, frekvenssi: 1\n", child60.toString());
        
    }
    
    
}