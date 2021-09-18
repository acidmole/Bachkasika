/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachkasika.domain;

import bachkasika.io.BachkasikaFileService;
import bachkasika.midi.MIDIParser;
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
            parser.parse();
            ArrayList<Note> sheet = parser.getMIDINotes();
            for (int i = 0; i < 20; i++) {
                System.out.println(sheet.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Virhe tapahtui Midin käsittelyssä.");

        }

    
    }
}
