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
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author hede
 */
public class BachkasikaService {
    
    public BachkasikaService() {
        try {
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
            Trie trie = new Trie(4);
            trie.insertFromNoteList(sheet);
            trie.find(0);
            System.out.println("Käytin aikaa " + (end - start)/1000.0 + " sekuntia.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Virhe tapahtui Midin käsittelyssä.");

        }

    
    }
}
