/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Bachkasika.trie;

import bachkasika.domain.Note;
import bachkasika.trie.*;
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
public class FrameNodeTest {
    private FrameNode node;
    private ArrayList<Note> noteList;
    
    public FrameNodeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        node = new FrameNode(50, 960, 960);
        noteList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Note n = new Note(0, 60 + i, 160, 160);
            noteList.add(n);
        }
        for (int i = 0; i < 10; i++) {
            Note n = new Note(0, 40 - i, 160, 160);
            noteList.add(n);
        }
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void addingChildrenWorksRight() {
        boolean[] children = node.getChildren();
        assertEquals(false, children[0]);
        assertEquals(false, children[1]);
        node.addChild(45, 960, 960);
        children = node.getChildren();
        assertEquals(true, children[0]);
        assertEquals(false, children[1]);
        node.addChild(55, 960, 960);
        children = node.getChildren();
        assertEquals(true, children[0]);
        assertEquals(false, children[1]);
        
        assertEquals(1, node.getBassChild().getDelayList().size());
        node.addChild(45, 220, 440);
        assertEquals(2, node.getBassChild().getDelayList().size());
    }
    
    @Test
    public void keyFittingWorksRight() {
        FrameNode rootNode = new FrameNode(50, 0, 0);
        for (int i = 0; i < noteList.size() - 3; i++) {
            FrameNode testRoot = rootNode;
            for (int j = 0; j < 2; j++) {
                Note n = noteList.get(i + j);
                node = node.addChild(n.getKey(), n.getDuration(), n.getDelay());
            }
        }
        
        ArrayList<Note> testList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Note n = new Note(0, 63 + i, 160, 160);
            testList.add(n);
        }
        
        testList = rootNode.fitKeysToFrame(testList, 0);
        
        
    }

}
