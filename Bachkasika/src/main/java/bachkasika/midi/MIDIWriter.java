/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bachkasika.midi;

import bachkasika.domain.Note;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.SysexMessage;
import javax.sound.midi.Track;

/**
 * This class owes big shoutout to Karl Brown for his tiny Java program:
 * http://www.automatic-pilot.com/midifile.html
 * @author hede
 */
public class MIDIWriter {
    
    public MIDIWriter() {
        
    }
    
    public File writeToMIDI(ArrayList<Note> noteList) throws InvalidMidiDataException, IOException {
                Sequence s = new Sequence(Sequence.PPQ, 120);
        Track t = s.createTrack();
        
        byte[] b = {(byte)0xF0, 0x7E, 0x7F, 0x09, 0x01, (byte)0xF7};
        SysexMessage sm = new SysexMessage();
	sm.setMessage(b, 6);
	MidiEvent me = new MidiEvent(sm,(long)0);
	t.add(me);
        
        //****  set omni on  ****
		ShortMessage mm = new ShortMessage();
		mm.setMessage(0xB0, 0x7D,0x00);
		me = new MidiEvent(mm,(long)0);
		t.add(me);

//****  set poly on  ****
		mm = new ShortMessage();
		mm.setMessage(0xB0, 0x7F,0x00);
		me = new MidiEvent(mm,(long)0);
		t.add(me);

//****  set instrument to Piano  ****
		mm = new ShortMessage();
		mm.setMessage(0xC0, 0x11, 0x00);
		me = new MidiEvent(mm,(long)0);
		t.add(me);

// note on      

                for (Note n : noteList) {
                    ShortMessage noteOn = new ShortMessage();
                    noteOn.setMessage(0x90, n.getKey(),0x60);
                    me = new MidiEvent(noteOn, n.getTick());
                    t.add(me);
                    ShortMessage noteOff = new ShortMessage();
                    noteOff.setMessage(0x80, n.getKey(), 0x40);
                    MidiEvent me2 = new MidiEvent(noteOff, n.getTick() + n.getDuration() - 1);
                    t.add(me2);
                }
//****  set end of track (meta event) 19 ticks later  ****
		MetaMessage mt = new MetaMessage();
        byte[] bet = {}; // empty array
		mt.setMessage(0x2F,bet,0);
		me = new MidiEvent(mt, (long)140);
		t.add(me);

//****  write the MIDI sequence to a MIDI file  ****
		File f = new File("markoved_bach.mid");
		MidiSystem.write(s,1,f);               
        return f;
    }
    
}
