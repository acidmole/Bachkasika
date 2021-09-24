/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachkasika.domain;

import bachkasika.io.BachkasikaFileService;
import bachkasika.midi.MIDIParser;
import bachkasika.trie.NoteNode;
import bachkasika.trie.Trie;
import java.util.ArrayList;

/**
 *
 * @author hede
 */
public class BachkasikaService {
    
    public BachkasikaService() {
        try {
            BachkasikaFileService bsFileService = new BachkasikaFileService("bwv539.mid");
            MIDIParser parser = new MIDIParser(bsFileService.getMidiFile());
            parser.parse(0);
            ArrayList<Note> sheet = parser.getMIDINotes();
            bsFileService.addNewFile("bwv588.mid");
            parser.setMidiFile(bsFileService.getMidiFile());
            parser.parse(0);
            ArrayList<Note> sheet2 = parser.getMIDINotes();
            Trie trie = new Trie();
            long start = System.currentTimeMillis();
            for (Note n : sheet) {
                trie.addNote(n);
            }
            for (Note n : sheet2) {
                trie.addNote(n);
            }
            long stop = System.currentTimeMillis();
            System.out.println("Notes:" + (sheet.size() + sheet2.size()));
            System.out.println("Parsing time: " + (stop - start) + " milliseconds");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Virhe tapahtui Midin käsittelyssä.");

        }

    
    }
}
