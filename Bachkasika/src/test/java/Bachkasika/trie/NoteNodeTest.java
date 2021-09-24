/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Bachkasika.trie;

import bachkasika.domain.Note;
import bachkasika.trie.NoteNode;
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
public class NoteNodeTest {
    
    NoteNode nn1;
    NoteNode nn2;
    
    public NoteNodeTest() {
    }
    
    @Before
    public void setUp() {
        nn1 = new NoteNode(new Note(0, 60, 100, 100));
        nn2 = new NoteNode(new Note(0, 60, 100, 100));
    }
    
    
    @Test
    public void noteNodeEqualsWorksRight() {
        assertEquals(true, nn1.equals(nn1));
        assertEquals(true, nn1.equals(nn2));
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
