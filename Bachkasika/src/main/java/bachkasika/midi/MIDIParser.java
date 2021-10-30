/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachkasika.midi;

import bachkasika.domain.Note;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javax.sound.midi.InvalidMidiDataException;
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
    private MIDIWriter writer;
    private final int NOTE_ON = 0x90;
    private final int NOTE_OFF = 0x80;
    
    /**
     * Luokka vastaa MIDI:n käsittelystä. Se pilkkoo valmiin MIDI:n
     * Note-olioista koostuvaksi listaksi. Se myös pystyy ohjaamaan listat
     * uusiksi MIDI-tiedostoiksi MIDIWriter-luokan avulla.
     * 
     * @param file parseroitava miditiedosto
     * @see MIDIWriter
     * @throws Exception jos parseroinnissa tapahtuu virhe
     */
    public MIDIParser(File file) throws IOException {
        this.midiFile = file;
        this.parsedMIDI = new ArrayList<>();
        this.writer = new MIDIWriter("markoved_bach.mid");
    }

    public MIDIParser() throws IOException {
        this.midiFile = null;
        this.parsedMIDI = new ArrayList<>();
        this.writer = new MIDIWriter("markoved_bach.mid");
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
                        Note newNote = new Note(tick, key + transpose, -1, 0);
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
                    }
                }
            }
        }
        this.sortAndTrimDurations();
        this.filterHighNotesFromList();
        this.trimDelays();
        return this.parsedMIDI;
    }
    
    public void setMidiFile(File file) {
        this.midiFile = file;
    }
    
    public void resetNoteList() {
        this.parsedMIDI.clear();
    }
    
    /**
     * Järjestää eri raidat ajanhetken perusteella kasvavaan järjestykseen sekä 
     * sovittaa nuottien pituudet. Poistaa staccatot, mutta myös tasapäistää 
     * erityyppiset nuotinnukset.
     * @return Järjestetty ja pituudet sovitettu Note-oliotaulukko
     */
    private ArrayList<Note> sortAndTrimDurations() {
        Collections.sort(this.parsedMIDI);
        ArrayDeque<Note> helperDeque = new ArrayDeque<>();
        long comparedTick = 0;
        Iterator<Note> iter = this.parsedMIDI.iterator();
        while (iter.hasNext()) {
            Note n = iter.next();
            if (n.getTick() > comparedTick) {
                while (!helperDeque.isEmpty()) {
                    Note comparedNote = helperDeque.pollFirst();
                    if (comparedNote.getDuration() < n.getTick() - comparedTick) {
                        comparedNote.setDuration(n.getTick() - comparedTick);
                    }
                }
                comparedTick = n.getTick();
            }
            helperDeque.add(n);
        }
        return this.parsedMIDI;
    }
     /**
     * Metodi, jonka tehtävä on palauttaa minkä tahansa Note-olioita
     * sisältävän listan samalla tickillä soivien nuottien korkein nuotti.
     * 
     * @param noteList Note-olion lista
     * @return korkeimmat nuotit sisältävä lista. jos ei ole tarpeeksi
     * elementtejä, palautetaan null
     */
   public ArrayList<Note> filterHighNotesFromList() {
        ArrayList<Note> helperList = new ArrayList<>();
        ArrayList<Note> finalList = new ArrayList<>();
        long comparedTick = 0;
        Iterator<Note> iter = this.parsedMIDI.iterator();
        while (iter.hasNext()) {
            Note n = iter.next();
            if (n.getTick() > comparedTick && !helperList.isEmpty()) {
                Note highestNote = Collections.max(helperList);
                finalList.add(highestNote);
                helperList.clear();
            }
            helperList.add(n);
            comparedTick = n.getTick();
        }
        if (!helperList.isEmpty()) {
            Note highestNote = Collections.max(helperList);
            finalList.add(highestNote);
        }
        this.parsedMIDI = finalList;
        return this.parsedMIDI;
    }
    
   /**
    * Asettaa Note-olion delay-parametrin oikean kokoiseksi nuottilistassa eli
    * katsoo milloin seuraava nuotti alkaa soimaan. Tämä voi olla lyhyempi kuin
    * nuotin kesto.
    * @return trimmattu lista
    */
    public ArrayList<Note> trimDelays() {
        if (this.parsedMIDI.isEmpty()) {
            return new ArrayList<Note>();
        }
        Note prevNote = this.parsedMIDI.get(0);
        ArrayList<Note> helperList = new ArrayList<>();
        for (int i = 1; i < this.parsedMIDI.size() - 1; i++) {
            Note nextNote = this.parsedMIDI.get(i);
            prevNote.setDelay(nextNote.getTick() - prevNote.getTick());
            helperList.add(prevNote);
            prevNote = nextNote;
        }
        this.parsedMIDI = helperList;
        return this.parsedMIDI;
    }
    
    public File writetoMIDI(ArrayList<Note> notes) throws InvalidMidiDataException, IOException {
        return this.writer.writeToMIDI(notes);
    }
}
