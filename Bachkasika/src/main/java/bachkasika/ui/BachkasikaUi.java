/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachkasika.ui;

import bachkasika.domain.BachkasikaService;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author hede
 */
public class BachkasikaUi extends Application {

    
    public void init() throws Exception {
        BachkasikaService bsService = new BachkasikaService();
    }

    @Override
    public void start(Stage stage) throws Exception {
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
