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
    
    public Trie() {
        this.nodesByKey = new ArrayList();
    }
    
    public void addNote(Note n) {
        if (!this.isNoteInNodeList(n)) {
            ArrayList<NoteNode> nodeList = this.nodesByKey.get(n.getKey());
            nodeList.add(new NoteNode(n));
            this.nodesByKey.add(n.getKey(), nodeList);
        }
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
