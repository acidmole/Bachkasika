/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midi;

import bachkasika.domain.Note;
import bachkasika.io.BachkasikaFileService;
import bachkasika.midi.MIDIParser;
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
public class MIDIParserTest {
    
    BachkasikaFileService bsFileService;
    MIDIParser testParser;
    
    public MIDIParserTest() {
    }
    
    @Before
    public void setUp() {
        try {
            bsFileService = new BachkasikaFileService("testMIDI.mid");
            testParser = new MIDIParser(bsFileService.getMidiFile());
        } catch (Exception e) {
        }
    }
    
    @Test
    public void parserParsesCorrectly() throws Exception {
        ArrayList<Note> testNotes = testParser.parse();
        assertEquals(testNotes.get(0).getKey(), 60);
        assertEquals(testNotes.get(1).getTick(), 1920);
        assertEquals(testNotes.get(2).getDuration(), 1920);
        
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
