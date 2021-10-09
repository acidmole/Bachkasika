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
        testRoot = new TrieNode(40, 99);
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
    
    @Test
    public void trieNodeCanBeFilledFromMiddle() {
        int[] testSeq = {12, 12, 12, -1, -1};
        int[] testFill = testRoot.fillSequence(3, testSeq, testRoot);
        assertEquals(12, testSeq[0]);
        assertEquals(12, testSeq[1]);
        assertEquals(12, testSeq[2]);
        assertFalse(testSeq[3] == -1);
        assertFalse(testSeq[4] == -1);
    }
    
    @Test
    public void findAndFillBranchReturnsFullArray() {
        int[] filledSeq = testRoot.findAndFillBranch(testSequence);
        assertEquals(62, testSequence[2]);
        assertEquals(63, testSequence[3]);
        assertEquals(64, testSequence[4]);
    }
    
    
}
