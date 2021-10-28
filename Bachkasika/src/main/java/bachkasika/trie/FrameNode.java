/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bachkasika.trie;

import bachkasika.domain.Note;
import java.util.ArrayList;
import java.util.Iterator;
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
    
    /**
     * Luokka, jonka tarkoitus on tuottaa erilaisia sävelten pituuksia
     * Trie-rakenteen muodossa. Ei tällä hetkellä ota huomioon tahdin 
     * päättymistä tai alkamista.
     * 
     * Lapset jaetaan joko basso- tai melodianuoteiksi.
     * 
     * @param bassNoteBoundary tätä ylemmät lasketaan melodianuoteiksi
     * @param durationLength nuotin soinnin kesto
     * @param delayLength kuinka pitkään kuluu ennen kuin toinen nuotti alkaa soimaan
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
    
    /**
     * Metodi kertoo onko solmulla lapsia
     * @return taulukko onko lapsia olemassa. 0 on basso, 1 on melodia.
     */
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
    
    public ArrayList<Note> fitKeysToFrame(int[] keyChain, int chainLength) {
        ArrayList<Note> noteList = this.initFrame(keyChain, chainLength);
        ArrayList<Note> helperList = new ArrayList<>();
        for (Note n : noteList) {
            helperList.add(n);
        }
        for (int i = chainLength; i < keyChain.length - chainLength; i++) {
            FrameNode parentNode = this;
            Note n = new Note(0, keyChain[i], 0, 0);
            helperList.add(n);
            Iterator<Note> iter = helperList.iterator();
            while(iter.hasNext()) {
                Note nextNote = iter.next();
                if (nextNote.getKey() <= this.bassNoteBoundary && parentNode.bassChild != null) {
                    parentNode = parentNode.bassChild;
                } else if (nextNote.getKey() > this.bassNoteBoundary && parentNode.trebleChild != null) {
                    parentNode = parentNode.trebleChild;
                } else {
                    parentNode = this;
                }
            }
            long[] durAndDelay = parentNode.getRandomDurationAndDelay();
            n.setDuration(durAndDelay[0]);
            n.setDelay(durAndDelay[1]);
            noteList.add(n);
            helperList.remove(0);
        }
        return noteList;
    }
    
    private ArrayList<Note> initFrame(int[] keyChain, int chainLength) {
        int i = 0;
        FrameNode parentNode = this;
        ArrayList<Note> initializedNoteList = new ArrayList<>();
        while (i < chainLength) {
            Note n = new Note(0, keyChain[i], 0, 0);
            if (keyChain[i] <= this.bassNoteBoundary && parentNode.bassChild != null) {
                parentNode = parentNode.bassChild;
                long[] dd = parentNode.getRandomDurationAndDelay();
                n.setDuration(dd[0]);
                n.setDelay(dd[1]);
            } else if (keyChain[i] > this.bassNoteBoundary && parentNode.trebleChild != null) {
                parentNode = parentNode.trebleChild;
                long[] dd = parentNode.getRandomDurationAndDelay();
                n.setDuration(dd[0]);
                n.setDelay(dd[1]);
            } else {
                 parentNode = this;
            }
            initializedNoteList.add(n);
            i++;
        }
        return initializedNoteList;
    }
    
    public ArrayList<Long> getDurationList() {
        return this.durationList;
    }
    
    public ArrayList<Long> getDelayList() {
        return this.delayList;
    }
    
    public FrameNode getBassChild() {
        return this.bassChild;
    }
    
    public FrameNode getTrebleChild() {
        return this.trebleChild;
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
    public long[] getRandomDurationAndDelay() {
        long[] dd = new long[2];
        Random rn = new Random();
        int rndObj = rn.nextInt(this.delayList.size());
        dd[0] = this.durationList.get(rndObj);
        dd[1] = this.delayList.get(rndObj);
        return dd;
    }
}
