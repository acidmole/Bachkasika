/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bachkasika.midi;

import bachkasika.domain.Note;
import bachkasika.io.BachkasikaFileService;
import bachkasika.midi.MIDIParser;
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
public class MIDIParserTest {
    
    BachkasikaFileService bsFileService;
    MIDIParser testParser;
    List<File> fileList;
    
    public MIDIParserTest() {
    }
    
    @Before
    public void setUp() {
        try {
            bsFileService = new BachkasikaFileService("test/");
            this.fileList = bsFileService.getFileList();
            testParser = new MIDIParser(bsFileService.getFileList().get(0));
        } catch (Exception e) {
        }
    }
    
    @Test
    public void parserParsesCorrectly() throws Exception {
        ArrayList<Note> testNotes = testParser.parse(0);
        System.out.println(testNotes);
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
