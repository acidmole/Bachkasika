/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachkasika.io;

import lombok.Data;
import java.io.File;
import java.util.Arrays;
import java.util.List;


/**
 * Tämä luokka vastaa tiedostojen käsittelystä.
 * 
 * @see 
 * 
 * @author hede
 */
@Data
public class BachkasikaFileService {
    
    private final File filePath;

    public BachkasikaFileService(String path) {
        this.filePath = new File(path);
    }
    
    public List<File> getFileList() {
        return Arrays.asList(this.filePath.listFiles());
    }
    
}
