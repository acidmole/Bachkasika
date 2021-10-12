/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachkasika.ui;

import bachkasika.domain.BachkasikaService;
import java.io.File;
import java.util.List;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author hede
 */
public class BachkasikaUi extends Application {
    
    private BachkasikaService bsService;

    
    @Override
    public void init() throws Exception {
        bsService = new BachkasikaService();
    }

    @Override
    public void start(Stage stage) throws Exception {

        List<File> fileList = bsService.getFileList();
        ListView midis = new ListView();
        midis.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        midis.getItems().addAll(fileList);
        
        ListView selectedMidis = new ListView();
        Button runButton = new Button("Suorita");
        Button confirm = new Button("Siirrä valinnat");
        Button reset = new Button("Tyhjennä");
        Label chains = new Label("Triessä ketjuja: 0");
        
        VBox midiBox = new VBox(midis);
        HBox topBox = new HBox(20);
        HBox buttonBox = new HBox(20);
        buttonBox.getChildren().add(confirm);
        buttonBox.getChildren().add(reset);
        topBox.getChildren().add(midis);
        topBox.getChildren().add(selectedMidis);
        topBox.getChildren().add(runButton);
        topBox.getChildren().add(chains);
        
        runButton.setOnAction(event -> {
            System.out.println(this.bsService.createMarkovChain(selectedMidis.getItems()));
            chains.setText("Triessä ketjuja: " + this.bsService.getChains());
        });

        confirm.setOnAction(event -> {
            ObservableList selection = midis.getSelectionModel().getSelectedItems();
            
            for (Object o : selection) {
                selectedMidis.getItems().add(o);
            }
        });
        
        reset.setOnAction(event -> {
            selectedMidis.getItems().clear();
        });
        
        
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topBox);
        borderPane.setCenter(buttonBox);
        Scene scene = new Scene(borderPane, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
