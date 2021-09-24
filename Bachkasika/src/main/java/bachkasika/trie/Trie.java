/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bachkasika.trie;

import bachkasika.domain.Note;
import java.util.ArrayList;

/**
 * 
 * @author hede
 * 
 * 
 */
public class Trie {
    
    private ArrayList<ArrayList<NoteNode>> nodesByKey;
    private NoteNode previousNode;

    public Trie() {
        this.nodesByKey = new ArrayList();
        this.previousNode = null;
    }
    
    public void addNote(Note n) {
        NoteNode newNode = new NoteNode(n);
        this.previousNode.insertChild(newNode);
        if (!this.isNoteInNodeList(n)) {
            ArrayList<NoteNode> nodeList = this.nodesByKey.get(n.getKey());
            nodeList.add(newNode);
            this.nodesByKey.add(n.getKey(), nodeList);
        }
        this.previousNode = newNode;
    }
    
    public boolean isNoteInNodeList(Note n) {
        for (NoteNode nn : this.nodesByKey.get(n.getKey())) {
            if (nn.equals(n)) {
                return true;
            }
        }
        return false;
    }
}
