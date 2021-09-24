/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bachkasika.trie;

import bachkasika.domain.Note;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author hede
 * 
 * 
 */
public class Trie {
    
    private ArrayList<ArrayList<NoteNode>> nodesByKey;
    private NoteNode previousNode;

    /**
     * Jokaiselle sävelkorkeudelle luodaan ArrayList, jossa on eripituisia
     * ja pitkälle soivia nuotteja.
     */
    public Trie() {
        this.nodesByKey = new ArrayList();
        for (int i = 0; i < 128; i++) {
            this.nodesByKey.add(i, new ArrayList<NoteNode>());
        }
        this.previousNode = null;
    }
    
    /**
     * Lisää sävelkorkeuden omaan listaansa, jos täsmälleen samanlaista ei 
     * jo ole olemassa.
     * @param n lisättävä nuotti
     */
    public void addNote(Note n) {
        NoteNode newNode = new NoteNode(n);
        if (this.previousNode != null) {
            this.previousNode.insertChild(newNode);
        }
        if (!this.isNoteInNodeList(newNode)) {
            ArrayList<NoteNode> nodeList = this.nodesByKey.remove(newNode.getKey());
            nodeList.add(newNode);
            this.nodesByKey.add(newNode.getKey(), nodeList);
        }
        this.previousNode = newNode;
    }
    
    /**
     * Luokka joka tarkastaa, löytyykö täsmälleen samanlaista nuottia
     * rakenteesta
     * @param n nuotti jota tarkastetaan
     * @return true, jos nuotti löytyi. Muuten false.
     */
    public boolean isNoteInNodeList(NoteNode n) {
        for (Iterator<NoteNode> it = this.nodesByKey.get(n.getKey()).iterator(); it.hasNext();) {
            NoteNode nn = it.next();
            if (nn.equals(n)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Palauttaa yhden sävelkorkeuden eri pituiset nuotit.
     * @param key sävelkorkeus
     * @return lista sävelkorkeuksista
     */
    public ArrayList<NoteNode> getNodesByKey(int key) {
        return this.nodesByKey.get(key);
    }
}
