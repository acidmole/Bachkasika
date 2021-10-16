/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bachkasika.trie;

import java.util.HashMap;

/**
 * Tämä luokka vastaa kehikko-Trien luomises, johon nuotit asetetaan
 * 
 *
 * @author hede
 */
public class FrameNode {
    
    private FrameNode bassChild;
    private FrameNode trebleChild;
    private HashMap<Long, Integer> durationMap;
    private HashMap<Long, Integer> delayMap;
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
        this.durationMap = new HashMap<>();
        this.delayMap = new HashMap<>();
        this.bassNoteBoundary = bassNoteBoundary;
        this.addDurationLengthToMap(durationLength);
        this.addDelayLengthToMap(delayLength);
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
    
    public void addDurationLengthToMap(long durationLength) {
        this.durationMap.put(durationLength, this.durationMap.get(durationLength) + 1);
    }
    
    public void addDelayLengthToMap(long delayLength) {
        this.delayMap.put(delayLength, this.delayMap.get(delayLength) + 1);
    }
    
    public HashMap<Long, Integer> getDurationMap() {
        return this.getDurationMap();
    }
    
    public HashMap<Long, Integer> getDelayMap() {
        return this.getDelayMap();
    }
    
    public int getKey() {
        return this.key;
    }
}
