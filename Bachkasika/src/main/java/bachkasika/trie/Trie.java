/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bachkasika.trie;

import bachkasika.domain.Note;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author hede
 * 
 * Luokka on verkko, joka tallentaa sävelkorkeudeltaan erilaisia nuotteja.
 * Sävelkorkeudeltaan samanlaiset nuotit voivat olla erilaisia kestoltaan ja
 * päällekkäisyydeltään, joten saman korkeuden sisältävät nuotit ovat
 * talletettu samaan listaan. Tallennettaessa vertaillaan,  jos täsmälleen 
 * samanlainen nuotti on jo rakenteessa. 
 * 
 * Jokaisella verkon erilaisella nuotilla on talletettu myös verkon
 * naapurisolmut, joihin voidaan siirtyä.
 */
public class Trie {
    
    private ArrayList<ArrayList<NoteNode>> nodesByKey;
    private NoteNode previousNode;

    public Trie() {
        this.nodesByKey = new ArrayList();
        for (int i = 0; i < 128; i++) {
            this.nodesByKey.add(i, new ArrayList<NoteNode>());
        }
        this.previousNode = null;
    }
    
    /**
     * Lisää sävelkorkeuden omaan listaansa, jos täsmälleen samanlaista ei 
     * jo ole olemassa. Lisätään samalla viite edellisestä NoteNode-oliosta
     * joko uuteen olioon tai jo olemassa olevaan.
     * @param n lisättävä nuotti
     */
    public void addNote(Note n) {
        NoteNode newNode = new NoteNode(n);
        NoteNode duplicateNode = this.isNoteInNodeList(newNode);
        if (duplicateNode != null) {
            ArrayList<NoteNode> nodeList = this.nodesByKey.remove(newNode.getKey());
            nodeList.add(newNode);
            this.nodesByKey.add(newNode.getKey(), nodeList);
            if (this.previousNode != null) {
                this.previousNode.insertChild(newNode);
            }
        } else {
            if(previousNode != null) {
                previousNode.insertChild(duplicateNode);
            }
        }
        this.previousNode = newNode;
    }
    
    /**
     * Luokka joka tarkastaa, löytyykö täsmälleen samanlaista nuottia
     * rakenteesta
     * @param n nuotti jota tarkastetaan
     * @return true, jos nuotti löytyi. Muuten false.
     */
    public NoteNode isNoteInNodeList(NoteNode n) {
        for (Iterator<NoteNode> it = this.nodesByKey.get(n.getKey()).iterator(); it.hasNext();) {
            NoteNode nn = it.next();
            if (nn.equals(n)) {
                return nn;
            }
        }
        return null;
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
