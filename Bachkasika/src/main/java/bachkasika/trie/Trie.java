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

    public Trie() {
        this.nodesByKey = new ArrayList();
        for (int i = 0; i < 128; i++) {
            this.nodesByKey.add(i, new ArrayList<NoteNode>());
        }
        this.previousNode = null;
    }
    
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
    
    public boolean isNoteInNodeList(NoteNode n) {
        for (Iterator<NoteNode> it = this.nodesByKey.get(n.getKey()).iterator(); it.hasNext();) {
            NoteNode nn = it.next();
            if (nn.equals(n)) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<NoteNode> getNodesByKey(int key) {
        return this.nodesByKey.get(key);
    }
}
