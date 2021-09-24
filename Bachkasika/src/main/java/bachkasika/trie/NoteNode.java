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
 */
public class NoteNode extends Note {
    
    private ArrayList<NoteNode> children;
    
    public NoteNode(Note n) {

        super(n.getTick(), n.getKey(), n.getDuration(), n.getDelay());
        
    }
    
    public Note getNote() {
       return new Note(this.getTick(), this.getKey(), this.getDuration(), this.getDelay());
    }
   
    public void insertChild(NoteNode nn) {
       this.children.add(nn);
    }
   
    public ArrayList<NoteNode> getChildren() {
       return this.children;
    }
    
    public boolean equals(NoteNode nn) {
        return this.equals(nn);
    }
    
    
}
