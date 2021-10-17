/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bachkasika.trie;

import bachkasika.domain.Note;
import java.util.ArrayList;
import java.util.Random;

/**
 * Tämä luokka vastaa kehikko-Trien solmujen luomisesta, johon nuotit asetetaan
 * 
 * @author hede
 */
public class FrameNode {
    
    private FrameNode bassChild; 
    private FrameNode trebleChild;
    private ArrayList<Long> durationList;
    private ArrayList<Long> delayList;
    private final int bassNoteBoundary;
    private boolean isBassNote;
    private int key;
    
    /**
     * Luokka, jonka tarkoitus on tuottaa erilaisia sävelten pituuksia
     * Trie-rakenteen muodossa. Ei tällä hetkellä ota huomioon tahdin 
     * päättymistä tai alkamista.
     * 
     * Lapset jaetaan joko basso- tai melodianuoteiksi.
     * 
     * @param bassNoteBoundary tätä ylemmät lasketaan melodianuoteiksi
     */
    public FrameNode(int bassNoteBoundary, long durationLength, long delayLength) {
        this.durationList = new ArrayList<>();
        this.delayList = new ArrayList<>();
        this.bassNoteBoundary = bassNoteBoundary;
        this.addDurationLengthToList(durationLength);
        this.addDelayLengthToList(delayLength);
    }
    
    /**
     * Metodi lisää lapsen ja jos se on jo olemassa, lisää lapselle tietoja
     * sen kestosta ja päällekkäisestä soitosta.
     * Metodi tarkistaa ensin annetun nuotin korkeuden ja määrittelee onko
     * kyseessä bassonuotti. Jos solmulla on jo vastaava lapsi, lisätään
     * lapsen tietoihin pituus ja päällekkäisyys.
     * 
     * @param key tarkasteltavan lapsen nuotin korkeus
     * @param durationLength nuotin kesto
     * @param delayLength seuraavaksi soitettavan nuotin alkamisaika
     * @return annettujen parametrien lapsi
     */
    public FrameNode addChild(int key, long durationLength, long delayLength) {
        if(key <= this.bassNoteBoundary) {
            if(this.bassChild == null) {
                this.bassChild = new FrameNode(this.bassNoteBoundary, durationLength, delayLength);
            } else {
                this.bassChild.addDelayLengthToList(delayLength);
                this.bassChild.addDurationLengthToList(durationLength);
            }
            return this.bassChild;
        } else {
            if(this.trebleChild == null) {
                this.trebleChild = new FrameNode(this.bassNoteBoundary, durationLength, delayLength);
            } else {
                this.trebleChild.addDelayLengthToList(delayLength);
                this.trebleChild.addDurationLengthToList(durationLength);
            }
            return this.trebleChild;
        }
    }
    
    public boolean[] getChildren() {
        boolean[] children = new boolean[2];
        if(this.bassChild != null) {
            children[0] = true;
        }
        if(this.trebleChild != null) {
            children[0] = true;
        }
        return children;
    }
    
    public ArrayList<Note> fitKeysToFrame(ArrayList<Note> framedKeySequence, int level) {
        if (level > framedKeySequence.size() - 1) {
            return framedKeySequence;
        }
        FrameNode child = new FrameNode(0,0,0);
        Note n = framedKeySequence.get(level);
        if (n.getKey() <= this.bassNoteBoundary) {
            if (this.bassChild != null) {
                child = this.bassChild;    
            } else child = this.trebleChild;
        } else {
            if (this.trebleChild != null) {
                child = this.trebleChild;
            } else child = this.bassChild;
        }
        
        if (n.getDuration() == 0) {
            long[] dd = child.getRandomDurationAndLength();
            n.setDuration(dd[0]);
            n.setDelay(dd[1]);
            framedKeySequence.add(level, n);
        }
        return child.fitKeysToFrame(framedKeySequence, level + 1);
    }
    

    
    
    public void addDurationLengthToList(long durationLength) {
        this.durationList.add(durationLength);
    }
    
    public void addDelayLengthToList(long delayLength) {
        this.delayList.add(delayLength);
    }
    
    /**
     * Arpoo solmusta jonkin nuotin duration- ja delay-arvot.
     * 
     * @return indeksi 0 on kesto, indeksi 1 on viive
     */
    public long[] getRandomDurationAndLength() {
        long[] dd = new long[2];
        Random rn = new Random();
        int rndObj = rn.nextInt(this.delayList.size());
        dd[0] = this.durationList.get(rndObj);
        dd[1] = this.delayList.get(rndObj);
        return dd;
    }
}
