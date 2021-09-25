/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bachkasika.trie;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author hede
 */
public class MarkovChain {
    
    private Trie trie;
    private ArrayList<NoteNode> chain;
    
    public MarkovChain(Trie trie) {
        this.trie = trie;
    }
    
    public ArrayList<NoteNode> formChain(int notes, boolean isInMajorKey) {
        ArrayList<NoteNode> newChain = new ArrayList<>();
        int key = this.getRightKey(isInMajorKey);
        int i = 1;
        NoteNode node = getRandomNoteByKey(key);
        newChain.add(node);
        while (i < notes || node.getChildren() != null) {
            node = this.getRandomNoteFromChildren(node);
            if (node == null) break;
            newChain.add(node);
            i++;
        }
        return newChain;
    }
    
    private int getRightKey(boolean isInMajorKey) {
        int key;
        if (isInMajorKey) {
            key = 2;
        } else key = 5;
        while(this.trie.getNodesByKey(key).isEmpty()) {
            key += 12;
            if (key > 127) {
                key -= 122;
            }
        }
        return key;

    }
    
    private NoteNode getRandomNoteByKey(int key) {
        Random rn = new Random();
        return this.trie.getNodesByKey(key).get(rn.nextInt(this.trie.getNodesByKey(key).size()));
        
    }
    
    private NoteNode getRandomNoteFromChildren(NoteNode n) {
        Random rn = new Random();
        if(n.getChildren().size() < 1 )
            return null;
        System.out.println(n.getChildren().size());
        return n.getChildren().get(rn.nextInt(n.getChildren().size()));
    }
    
}
