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
    private MIDIParser parser;
    private Trie trie;
    private MarkovChain chain;
    
    public BachkasikaService() {
        
        try {
            this.bsFileService = new BachkasikaFileService("midis/");
            this.fileList = this.bsFileService.getFileList();
            this.parser = new MIDIParser();
            this.trie = new Trie(5, 50);
            this.chain = new MarkovChain(trie);
        } catch (Exception e) {
            System.out.println("I/O-poikkeus tiedostonkäsittelyssä.");
        }
    }
    
    /**
     * Tämä luokka ottaa tarjoaa käyttöliittymälle rajapinnan
     * ja käsittelee tarjotun tiedostolistan sisältämät MIDI:t syöttämällä ne
     * MIDI-parseriin ja tästä eteenpäin Triehen.
     * 
     * @param midiList File-lista käsiteltävistä mideistä
     * @param transpose montako askelta transponoidaan
     * @return String-olio käyttöliittymälle onnistuiko käsittely
     */
    public String createMarkovChain(List<File> midiList, int transpose) {
        if (midiList == null || midiList.size() == 0) {
            return "Ei käsiteltävää.";
        }
        try {
            for (File f : midiList) {
                this.parser.resetNoteList();
                this.parser.setMidiFile(f);
                this.parser.parse(transpose);
                this.trie.insertFromNoteList(this.parser.getMIDINotes());
            }
            ArrayList<Note> createdNoteList = chain.createNoteListFromKeyChain(chain.createKeyChain(60));
            System.out.println(createdNoteList);
            
            return "MIDI ok.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Midin parserointi epäonnistui: " + e.getMessage();
            
        }
    }
    
    public int getChains() {
        return this.trie.getChains();
    }
    
    public BachkasikaFileService getFileService() {
        return this.bsFileService;
    }
    
    public List<File> getFileList() {
        return this.fileList;
    }
    
}
