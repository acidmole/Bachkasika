/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachkasika.domain;

import bachkasika.io.BachkasikaFileService;
import bachkasika.midi.MIDIParser;
import bachkasika.trie.MarkovChain;
import bachkasika.trie.Trie;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author hede
 */
public class BachkasikaService {
    
    private BachkasikaFileService bsFileService;
    private List<File> fileList;
    
    public BachkasikaService() throws Exception {
        
            BachkasikaFileService bsFileService = new BachkasikaFileService();
            this.fileList = bsFileService.getFileList();
            /*
            long start = System.currentTimeMillis();
            BachkasikaFileService bsFileService = new BachkasikaFileService("bwv539.mid");
            MIDIParser parser = new MIDIParser(bsFileService.getMidiFile());
            parser.parse(0);
            ArrayList<Note> sheet = parser.getMIDINotes();
            bsFileService.addNewFile("bwv588.mid");
            parser.setMidiFile(bsFileService.getMidiFile());
            parser.parse(0);
            ArrayList<Note> sheet2 = parser.getMIDINotes();
            long end = System.currentTimeMillis();
            Trie trie = new Trie(5);
            trie.insertFromNoteList(sheet);
            trie.insertFromNoteList(sheet2);
            MarkovChain mc = new MarkovChain(trie);
            System.out.println(Arrays.toString(mc.createChain(30)));
            
            System.out.println("Käytin aikaa " + (end - start) / 1000.0 + " sekuntia.");
            */
    }
    
    public void createMarkovChain() {
        System.out.println("Ketjusaha käynnissä");
    }
    
    public BachkasikaFileService getFileService() {
        return this.bsFileService;
    }
    
    public List<File> getFileList() {
        return this.fileList;
    }
    
}
