/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Bachkasika.trie;

import bachkasika.trie.TrieNode;
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
public class TrieNodeTest {
    TrieNode testRoot;
    int[] testSequence;
    
    public TrieNodeTest() {
        
    }
    
    @Before
    public void setUp() {
        testRoot = new TrieNode();
        testSequence = new int[5];
        for (int i=0; i < 5; i++) {
            testSequence[i] = i + 60;
        }
        testRoot.addChildren(testSequence, 0);
    }
    
    @Test
    public void fillSequenceFillsRight() {
        int[] testFill = testRoot.fillSequence(0, new int[5], testRoot);
        assertEquals(60, testFill[0]);
    }
    
}
