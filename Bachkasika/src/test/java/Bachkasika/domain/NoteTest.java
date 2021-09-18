/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bachkasika.domain;

import bachkasika.domain.Note;
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
public class NoteTest {
    
    private Note note;
    private Note note2;
    private Note note3;
    private Note note4;
    
    public NoteTest() {
    }
    
    
    @Before
    public void setUp() {
        note = new Note(0, 20, 128, 128);
        note2 = new Note(0, 21, 128, 128);
        note3= new Note(0, 20, 18, 128);
        note4 = new Note(0, 20, 128, 125);
        
    }
    
    @Test
    public void noteEqualsWorksRight() {
        assertEquals(this.note.equals(this.note), 0);
        assertEquals(this.note.equals(note2), -1);
        assertEquals(this.note.equals(note3), -1);
        assertEquals(this.note.equals(note4), -1);
    
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
