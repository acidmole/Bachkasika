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
    
    /**
     * Tämä luokka tarjoaa käyttöliittymälle rajapinnan MIDI:n käsittelyyn,
     * Trien luomiseen ja Markovin ketjujen rakentamiseen.
     * 
     * @param pathName midien sijainti hakemistossa
     */
    public BachkasikaService(String pathName) {
        
        try {
            this.bsFileService = new BachkasikaFileService(pathName);
            this.fileList = this.bsFileService.getFileList();
            this.parser = new MIDIParser();
            this.chain = new MarkovChain(null);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("I/O-poikkeus tiedostonkäsittelyssä.");
        }
    }
    
    /**
     * Ottaa vastaan käsiteltävät tiedostot, vie ne parseroitavaksi,
     * syöttää triehen ja rakentaa ketjun.
     * 
     * @param midiList File-lista käsiteltävistä mideistä
     * @param transpose montako askelta transponoidaan
     * @param depth monennenko asteen Markov tehdään
     * @param notes luotavien MIDI-nuottien määrä
     * @return String-olio käyttöliittymälle onnistuiko käsittely
     */
    public String createMarkovChain(List<File> midiList, int transpose, int depth, int notes) {
        if (midiList == null || midiList.size() == 0) {
            return "Ei käsiteltävää.";
        }
        if (this.trie == null) {
            this.createTrie(depth);
        }
        try {
            for (File f : midiList) {
                this.parser.resetNoteList();
                this.parser.setMidiFile(f);
                this.parser.parse(transpose);
                this.trie.insertFromNoteList(this.parser.getMIDINotes());
            }
            this.chain.setTrie(this.trie);
            ArrayList<Note> createdNoteList = chain.createNoteListFromKeyChain(chain.createKeyChain(notes));
            System.out.println(createdNoteList);
            String success = this.writeToFile(createdNoteList);
            return "MIDI ok.\n" + success;
        } catch (Exception e) {
            return "Midin parserointi epäonnistui: " + e.getMessage();
        }
    }
    
    /**
     * Vie valmiin nuottilistan MIDI:n kirjoitusta varten.
     * @param noteList nuottilista
     * @return viesti käyttöliittymälle onnistuiko kirjoittaminen
     */
    public String writeToFile(ArrayList<Note> noteList) {
        try {
            this.parser.writetoMIDI(noteList);
            return "Tiedoston kirjoitus onnistui.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Tiedoston kirjoitus epäonnistui.";
        }
    }
    
    public String createTrie(int depth) {
        this.trie = new Trie(depth, 59);
        return "Trie luotu.";
    }
    
    public int getChains() {
        if (this.trie == null) {
            return 0;
        }
        return this.trie.getChains();
    }
    
    public BachkasikaFileService getFileService() {
        return this.bsFileService;
    }
    
    public List<File> getFileList() {
        return this.fileList;
    }
    
    public void setTrie(Trie trie) {
        this.trie = trie;
    }

    public Trie getTrie() {
        return this.trie;
    }
    
}
