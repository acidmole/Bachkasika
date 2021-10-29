/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Bachkasika.domain;

import bachkasika.domain.BachkasikaService;
import bachkasika.io.BachkasikaFileService;
import bachkasika.midi.MIDIParser;
import bachkasika.trie.MarkovChain;
import bachkasika.trie.Trie;
import java.io.File;
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
public class BachkasikaServiceTest {
    
    BachkasikaService bssTest;
    BachkasikaFileService bsFileService;
    List<File> fileList;
    MIDIParser parser;
    Trie trie;

    
    public BachkasikaServiceTest() {
    }
    
    @Before
    public void setUp() {
        try {
            bssTest = new BachkasikaService();
            BachkasikaFileService bsFileService = new BachkasikaFileService("midis/");
            fileList = bsFileService.getFileList();
            parser = new MIDIParser();
            trie = new Trie(5, 50);
        } catch (Exception e) {
            System.out.println("I/O-poikkeus tiedostonkäsittelyssä.");
        }
    }
    /*
    @Test
    public void emptyMIDIListIsNotAccepted() {
        assertEquals("Ei käsiteltävää.", bssTest.createMarkovChain(null, 0));
    }
    
    @Test
    public void chainCreatorReturnsOk() {
        assertEquals("MIDI ok.", bssTest.createMarkovChain(fileList, 0));
    }
    */
}
