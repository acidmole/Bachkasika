/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Bachkasika.domain;

import bachkasika.domain.BachkasikaService;
import bachkasika.trie.Trie;
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
    
    public BachkasikaServiceTest() {
    }
    
    @Before
    public void setUp() {
        bssTest = new BachkasikaService("midis/");
    }
    
    
    @Test
    public void emptyMIDIListIsNotAccepted() {
        assertEquals("Ei käsiteltävää.", bssTest.createMarkovChain(null, 0, 0, 0));
    }
    
    @Test
    public void nullTreeIsCreated() {
        Trie trie = null;
        bssTest.setTrie(trie);
        System.out.println(bssTest.getFileList());
        bssTest.createMarkovChain(bssTest.getFileList(), 0, 2, 3);
        assertNotNull(bssTest.getTrie());
    }
    
    @Test
    public void nullTreeReturnsZeroChains() {
        Trie trie = null;
        assertEquals(0, bssTest.getChains());
    }
    
    
}
