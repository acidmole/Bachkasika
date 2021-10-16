/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bachkasika.trie;

import java.util.ArrayList;
import java.util.Random;

/**
 * Tämä luokka vastaa kehikko-Trien luomises, johon nuotit asetetaan
 * 
 * EI TOIMI VIELÄ
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
     * @param bassNoteBoundary tätä ylemmät lasketaan melodianuoteiksi
     */
    public FrameNode(int bassNoteBoundary, int key, long durationLength, long delayLength) {
        this.durationList = new ArrayList<>();
        this.delayList = new ArrayList<>();
        this.bassNoteBoundary = bassNoteBoundary;
        
        this.addDurationLengthToList(durationLength);
        this.addDelayLengthToList(delayLength);
        this.key = key;
    }
    
    public void addChild(FrameNode node) {
        if(node.key <= this.bassNoteBoundary) {
            if(this.bassChild == null) {
                this.bassChild = node;
            }
        } else {
            if(this.bassChild == null) {
                this.trebleChild = node;    
            }
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
    
    public void addDurationLengthToList(long durationLength) {
        this.durationList.add(durationLength);
    }
    
    public void addDelayLengthToList(long delayLength) {
        this.delayList.add(delayLength);
    }
    
    public int getKey() {
        return this.key;
    }
    
    public long getRandomDuration() {
        
        Random rn = new Random();
        return this.durationList.get(rn.nextInt(this.durationList.size()));
    }
    
    public long getRandomDelay() {
        Random rn = new Random();
        return this.delayList.get(rn.nextInt(this.durationList.size()));
    }
}
