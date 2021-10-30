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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author hede
 */
public class BachkasikaUi extends Application {
    
    private BachkasikaService bsService;
    private int transpose;
    private int depth;

    
    @Override
    public void init() throws Exception {
        bsService = new BachkasikaService("midis/");
        this.transpose = 0;
        this.depth = 1;
    }

    @Override
    public void start(Stage stage) throws Exception {

        List<File> fileList = bsService.getFileList();
        ListView midis = new ListView();
        midis.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        midis.getItems().addAll(fileList);
        
        ListView selectedMidis = new ListView();
        Label chains = new Label("Triessä ketjuja: 0");
        Label transposeLabel = new Label("Transponoidaan 0 sävelaskelta");
        Button runButton = new Button("Suorita");
        Button confirm = new Button("Siirrä valinnat");
        Button reset = new Button("Tyhjennä");
        Button incTranspose = new Button("+");
        Button decTranspose = new Button("-");
        Label prompt = new Label("Bachkasika running");
        prompt.setPrefSize(800, 200);
        prompt.setTextAlignment(TextAlignment.LEFT);
        prompt.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        Button incDepth = new Button("+");
        Button decDepth = new Button("-");
        Button createTrie = new Button("Rakenna Trie");
        Label depthLabel = new Label("Syvyys: 1");
        TextField insertNotes = new TextField("200");
        Label notes = new Label("Nuotteja: 200");
                
        HBox topBox = new HBox(20);
        HBox buttonBox = new HBox(20);
        HBox depthBox = new HBox(20);
        VBox notesBox = new VBox(20);
        VBox paramBox = new VBox(20);
        
        
        notesBox.getChildren().add(notes);
        notesBox.getChildren().add(insertNotes);
        depthBox.getChildren().add(incDepth);
        depthBox.getChildren().add(decDepth);
        depthBox.getChildren().add(depthLabel);
        paramBox.getChildren().add(runButton);
        paramBox.getChildren().add(createTrie);
        paramBox.getChildren().add(chains);
        paramBox.getChildren().add(depthBox);
        paramBox.getChildren().add(notesBox);
        buttonBox.getChildren().add(confirm);
        buttonBox.getChildren().add(reset);
        buttonBox.getChildren().add(incTranspose);
        buttonBox.getChildren().add(decTranspose);
        buttonBox.getChildren().add(transposeLabel);
        topBox.getChildren().add(midis);
        topBox.getChildren().add(selectedMidis);
        topBox.getChildren().add(paramBox);
        runButton.setOnAction(event -> {
            
            prompt.setText(prompt.getText() + "\nParseroidaan ja syötetään MIDI:t, transpoosi " 
                    + this.transpose + " sävelaskelta.\n" + 
                    this.bsService.createMarkovChain(selectedMidis.getItems(), 
                            this.transpose, this.depth, 
                            Integer.valueOf(notes.getText().substring(10))));
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
        
        createTrie.setOnAction(event -> { 
            prompt.setText(prompt.getText() + "\n" + this.bsService.createTrie(this.depth));
        });
        
        incTranspose.setOnAction(event -> {
            this.transpose++;
            transposeLabel.setText("Transponoidaan " + this.transpose + " sävelaskelta");
        });

        decTranspose.setOnAction(event -> {
            this.transpose--;
            transposeLabel.setText("Transponoidaan " + this.transpose + " sävelaskelta");
        });
        
        incDepth.setOnAction(event -> {
            this.depth++;
            depthLabel.setText("Syvyys: " + this.depth);
        });

        decDepth.setOnAction(event -> {
            if (this.depth > 1) {
                this.depth--;
                depthLabel.setText("Syvyys: " + this.depth);
            }
        });
        
        insertNotes.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> change, 
                    String oldValue, String newValue) {
                if(newValue.matches("[0-9]*")) {
                    notes.setText("Nuotteja: " + newValue);
                }
            }
        });
        
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topBox);
        borderPane.setCenter(buttonBox);
        borderPane.setBottom(prompt);
        Scene scene = new Scene(borderPane, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
    
}
