/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beroepsproduct;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author luukw
 */
public class Patientsearch {

       
    
        public Patientsearch(Stage stage, Scene sceneLogin) {
        
        
        
        GridPane gridPaneSearch = new GridPane();
        
        Scene sceneSearch = new Scene(gridPaneSearch, 800, 500);
        // Position the pane at the center of the screen, both vertically and horizontally
        gridPaneSearch.setAlignment(Pos.CENTER);
        
        // Set a padding of 20px on each side
        gridPaneSearch.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPaneSearch.setHgap(10);

        // Set the vertical gap between rows
        gridPaneSearch.setVgap(10);

        // Add Column Constraints

        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(100,100, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPaneSearch.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
        
        //Database toevoegen
        Database db = new Database(); 
                // Add Header
        Label headerLabel = new Label("Zoek patiënt");
        headerLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        gridPaneSearch.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        // Add Search Text Field
        TextField searchField = new TextField();
        searchField.setPrefHeight(40);
        searchField.setMaxWidth(500);
        GridPane.setHalignment(searchField, HPos.CENTER);
        gridPaneSearch.add(searchField, 1,1);

        // Add Search Button
        Button searchButton = new Button("Zoeken");
        
        //Button Action
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Database db = new Database();
       
       String patientZoek = searchField.getText();
       
       //check of de zoek balk leeg is
       if(!patientZoek.isEmpty())
       {
                    try {
                        boolean bool = db.SPatient(patientZoek);
                        
                        if(bool){
                            Patientinformatie patientinfo = new Patientinformatie(stage, sceneSearch);
                            
                        }else{
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Er ging iets fout!");
                            alert.setContentText("Deze Patiënt bestaat niet");
                            alert.showAndWait();
                        }       } catch (SQLException ex) {
                        Logger.getLogger(Patientsearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
       }else{
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Er ging iets fout!");
        alert.setContentText("U moet een Patiënt nummer invoeren");
        alert.showAndWait();
       }
            }
        });
        
              
        searchButton.setPrefHeight(40);
        searchButton.setDefaultButton(true);
        searchButton.setPrefWidth(150);
        gridPaneSearch.add(searchButton, 0, 4, 2, 1);
        GridPane.setHalignment(searchButton, HPos.CENTER);
        GridPane.setMargin(searchButton, new Insets(0, 0, 0, 0));
        
        
        // Add Logout Button
        Button logoutButton = new Button("Logout");
        
        //Button Action
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneLogin);
                }
        });
        
        logoutButton.setPrefHeight(40);
        logoutButton.setDefaultButton(true);
        logoutButton.setPrefWidth(150);
        gridPaneSearch.add(logoutButton, 0, 5, 2, 1);
        GridPane.setHalignment(logoutButton, HPos.CENTER);
        GridPane.setMargin(logoutButton, new Insets(0, 0, 0, 0));
        
        
         // Add Add Button
        Button AddButton = new Button("Patient Toevoegen");
        
        //Button Action
        AddButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                AddPatient patientadd = new AddPatient(stage, sceneSearch);
            }
        });
        
        AddButton.setPrefHeight(40);
        AddButton.setDefaultButton(true);
        AddButton.setPrefWidth(150);
        gridPaneSearch.add(AddButton, 0, 6, 2, 1);
        GridPane.setHalignment(AddButton, HPos.CENTER);
        GridPane.setMargin(AddButton, new Insets(0, 0, 0, 0));
        
        
        
        
	        stage.setScene(sceneSearch);
	
                }

                
                }
       
                

