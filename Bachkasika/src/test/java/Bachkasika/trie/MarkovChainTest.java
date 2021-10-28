/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Bachkasika.trie;

import bachkasika.domain.Note;
import bachkasika.io.BachkasikaFileService;
import bachkasika.midi.MIDIParser;
import bachkasika.trie.*;
import java.io.File;
import java.util.ArrayList;
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
public class MarkovChainTest {
    
    private MIDIParser testParser;
    private BachkasikaFileService bsFileService;
    private List<File> fileList;
    private Trie testTrie;
    private MarkovChain testMarkov;

    public MarkovChainTest() {
    }

    @Before
    public void setUp() {
        try {
            bsFileService = new BachkasikaFileService("midis/bwv588.mid");
            this.fileList = bsFileService.getFileList();
            testParser = new MIDIParser();
            this.testParser.resetNoteList();
            for (File f : this.fileList) {
                this.testParser.setMidiFile(f);
                this.testParser.parse(0);
            }
        } catch (Exception e) {
            System.out.println("Virhe MIDI:ssä");
        }
        testTrie = new Trie(8, 50);
        this.testTrie.insertFromNoteList(this.testParser.getMIDINotes());
        this.testMarkov = new MarkovChain(testTrie);
        

    }
    
    @Test
    public void markovChainBuildsSomething() {
        ArrayList<Note> notelist = testMarkov.createNoteListFromKeyChain(testMarkov.createKeyChain(80));
        for (Note n : notelist) {
            System.out.println(n);
        }
    }
}
