/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachkasika.io;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.io.File;


/**
 * Tämä luokka vastaa tiedostojen käsittelystä.
 * 
 * @see 
 * 
 * @author hede
 */
@Data
public class BachkasikaFileService {

    private File midiFile;
    
    public BachkasikaFileService(String fileName) {
        this.midiFile = new File(fileName);        
    }
    
    public void addNewFile(String name) {
        this.midiFile = new File(name);
    }
    
}
