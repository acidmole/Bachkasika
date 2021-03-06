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
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    List<File> fileList;
    
    public TrieTest() {
    }
    
    @Before
    public void setUp() {
        try {
            bsFileService = new BachkasikaFileService("midis/");
            this.fileList = bsFileService.getFileList();
            testParser = new MIDIParser();
        } catch (Exception e) {
            System.out.println("Virhe MIDI:ssä");
        }
        trie = new Trie(8, 50);
    }
    
    @Test
    public void trieBuildsSequencesRight() {
        try {
            this.testParser.resetNoteList();
            for (File f : this.fileList) {
                this.testParser.setMidiFile(f);
                this.testParser.parse(0);
            }
            this.trie.insertFromNoteList(this.testParser.getMIDINotes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertTrue(trie.getRoot().toString().length() > 500);
    }
    
    @Test
    public void randomSequenceIsNotEmpty() {
        try {
            this.testParser.resetNoteList();
            for (File f : this.fileList) {
                this.testParser.setMidiFile(f);
                this.testParser.parse(0);
            }
            this.trie.insertFromNoteList(this.testParser.getMIDINotes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        int[] randomSeq = trie.getRandomSequence();
        int[] randomSeq2 = trie.getRandomSequence();
        assertTrue(randomSeq[5] > 0); 
        assertTrue(randomSeq[6] > 0); 
        assertTrue(randomSeq[7] > 0); 
        assertTrue(randomSeq[4] > 0); 
        assertFalse(Arrays.toString(randomSeq).equals(Arrays.toString(randomSeq2)));
    }
    
    @Test
    public void fillDoesNotFillArraysTooSmall() {
        int[] testSeq = new int[5];
        testSeq[0] = 60;
        int[] filledSeq = trie.fill(testSeq, 10);
        assertEquals(10, filledSeq.length);
        assertEquals(0, filledSeq[0]);
    }
    
}