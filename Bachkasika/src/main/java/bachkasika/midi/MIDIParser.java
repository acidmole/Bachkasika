/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachkasika.midi;

import bachkasika.domain.Note;
import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

/**
 *  * 
 * Tämä luokka vastaa MIDI-tiedoston parseroinnista. Oliosta saa getMIDIList()-
 * funktiolla List-olion, joka sisältää toisen List-olion, jossa on 
 * MIDI-tiedoston samaan aikaan soitettavat nuotit.
 * 
 * MIDI:n parseroinnissa lähdemateriaalina on ollut Sami Koivun materiaali: 
 * https://stackoverflow.com/questions/3850688/reading-midi-files-in-java#comment4093620_3850885
 * 
 * 
 */

public class MIDIParser {
    
    private ArrayList<Note> parsedMIDI;
    private File midiFile;
    private final int NOTE_ON = 0x90;
    private final int NOTE_OFF = 0x80;
    
    /**
     *
     * @param file parseroitava miditiedosto
     * @throws Exception jos parseroinnissa tapahtuu virhe
     */
    public MIDIParser(File file) throws Exception {
        this.midiFile = file;
        this.parsedMIDI = new ArrayList<>();
    }
    
    /**
     * @return valmis lista kaikista soitetuista nuoteista ja niiden pituuksista
     */
    public ArrayList<Note> getMIDINotes() {
        return this.parsedMIDI;
    }
    
    /**
     * Tämä metodi vastaa MIDI-tiedoston parseroinnista nuottitasolle.
     * 
     * Koska nuotteja saattaa esiintyä päällekkäin (eli toinen nuotti soitetaan
     * vielä toisen soidessa) tallennetaan jokainen soitettu nuotti jonoon
     * odottamaan, että sen soitto päättyy.
     * 
     * Kaikki nuotit tallennetaan jatkossa kanavalle 0 ja velocity ON = 127
     * sekä velocity OFF = 64.
     * 
     * @param transpose kuinka monta sävelaskelta transponoidaan
     * @return ArrayList<Note> kaikki nuotit mallinnettuna pituuden sekä 
     * seuraavan nuotin soittoajankohdan mukaan
     * @throws Exception kaikissa parseroinnin ja MIDI-tiedoston virhetilanteissa
     */
    public ArrayList<Note> parse(int transpose) throws Exception {
        
        transpose = transpose % 12;
        ArrayDeque<Note> noteDeque = new ArrayDeque<>();
        Sequence sequence = MidiSystem.getSequence(this.midiFile);

        int trackNumber = 0;
        for (Track track :  sequence.getTracks()) {
            trackNumber++;
            for (int i = 0; i < track.size(); i++) { 
                MidiEvent event = track.get(i);
                long tick = event.getTick();
                MidiMessage message = event.getMessage();
                if (message instanceof ShortMessage) {
                    ShortMessage sm = (ShortMessage) message;

                    if (sm.getCommand() == NOTE_ON) {
                        int key = sm.getData1();
                        Note newNote = new Note(tick, key+transpose, -1, 0);
                        if (!noteDeque.isEmpty()) {
                            noteDeque.peekLast().setDelay(tick - noteDeque.peekLast().getTick());
                        }
                        noteDeque.add(newNote);
                    } else if (sm.getCommand() == NOTE_OFF) {
                        int key = sm.getData1();
                        Iterator<Note> iter = noteDeque.iterator();
                        while (iter.hasNext()) {
                            Note compNote = iter.next();
                            if (compNote.getKey() == key) {
                                compNote.setDuration(tick - compNote.getTick());
                                this.parsedMIDI.add(compNote);
                                noteDeque.remove(compNote);
                                break;
                            }
                        }
                    }                 }
            }
            System.out.println();
        }
        return this.parsedMIDI;
    }
    
    public void setMidiFile(File file) {
        this.midiFile = file;
    }
    
    
}
