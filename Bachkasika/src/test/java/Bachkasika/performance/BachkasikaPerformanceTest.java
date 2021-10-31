/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Bachkasika.performance;

import bachkasika.domain.Note;
import bachkasika.midi.MIDIParser;
import bachkasika.midi.MIDIWriter;
import bachkasika.trie.MarkovChain;
import bachkasika.trie.Trie;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author hede
 */
public class BachkasikaPerformanceTest {

    /**
     * Program performance tests
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int trieSize = 8;
        MIDIWriter testWriter = new MIDIWriter("performancetest.mid");
        Random rnd = new Random();
        ArrayList<Note> randomNoteList = new ArrayList<>();
        for (int i = 0; i < 300000; i++) {
            Note n = new Note(i * 60, rnd.nextInt(10) + 35, 60, 60);
            randomNoteList.add(n);
        }
        
        // midikirjoittajan suorituskykytesti
        long start = System.currentTimeMillis();
        try {
            testWriter.writeToMIDI(randomNoteList);
        } catch (Exception e) {
            System.out.println("Virhe writerissa.");
            e.printStackTrace();
        }
        long stop = System.currentTimeMillis();
        System.out.println("MIDI:n kirjoittamiseen aikaa kului " + (stop - start) / 1000.0 + " sekuntia.");
        
        
        start = System.currentTimeMillis();
        try {
            MIDIParser testParser = new MIDIParser(new File("performancetest.mid"));
            randomNoteList = testParser.parse(0);
        } catch (Exception e) {
            System.out.println("Virhe parserissa.");
            e.printStackTrace();
        }
        stop = System.currentTimeMillis();
        System.out.println("MIDI:n parserointiin aikaa kului " + (stop - start) / 1000.0 + " sekuntia.");
        
        start = System.currentTimeMillis();
        Trie trie = new Trie(trieSize, 59);
        trie.insertFromNoteList(randomNoteList);
        stop = System.currentTimeMillis();
        System.out.println("Trien rakentamiseen aikaa kului " + (stop - start) / 1000.0 + " sekuntia.");

        MarkovChain testMarkov = new MarkovChain(trie);
        start = System.currentTimeMillis();
        int[] markovArray = testMarkov.createKeyChain(randomNoteList.size());
        ArrayList<Note> markov = testMarkov.createNoteListFromKeyChain(markovArray);
        stop = System.currentTimeMillis();
        System.out.println("Markovin ketjun rakentamiseen aikaa kului " + (stop - start) / 1000.0 + " sekuntia.");
        

        for (int j = 0; j < 5; j++) {
            int[] c = new int[trieSize];
            int randomStart = rnd.nextInt(randomNoteList.size()) - trieSize;
            for (int i = 0; i < trieSize; i++) {
                c[i] = randomNoteList.get(i + randomStart).getKey();
            }
            int hits = 0;
            for (int i = 0; i < randomNoteList.size(); i++) {
                if (randomNoteList.get(i).getKey() == c[hits]) {
                    hits ++;
                    if (hits == 8) {
                        System.out.println("Löytyi vastaava ketju!");
                        break;
                    }
                } else {
                    hits = 0;
                }
            }
        }
        
    }
    
    
    
}
