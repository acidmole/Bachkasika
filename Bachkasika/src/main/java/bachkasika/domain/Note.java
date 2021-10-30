/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachkasika.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * Luokka, joka vastaa yksittäisen nuotin informaatiosta
 * 
 * tick on aika-arvo nuotin soitolle
 * key on äänen korkeus
 * duration on kesto
 * delay on viive ennen seuraavan nuotin soittamista
 * 
 */
@AllArgsConstructor @NoArgsConstructor @Data
public class Note implements Comparable<Note> {
    
    private long tick;
    private int key;
    private long duration;
    private long delay;

    
    /**
     *
     * @param anotherNote vertailtava nuotti
     * @return true jos täysin sama, muuten false
     */
    public boolean equals(Note anotherNote) {
        if (this.key == anotherNote.getKey()) {
            if (this.duration == anotherNote.getDuration()) {
                if (this.delay == anotherNote.getDelay()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Tick: " + this.tick + ", key: " + this.key + ", duration: " 
                + this.duration + ", delay " + this.delay;
    }

    @Override
    public int compareTo(Note t) {
        
        if ((int) this.tick - (int) t.getTick() == 0) {
            return this.key - t.getKey();
        }
        return ((int) this.tick - (int) t.getTick());
    }
    
}
