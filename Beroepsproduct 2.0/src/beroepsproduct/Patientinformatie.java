/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beroepsproduct;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author luukw
 */
public class Patientinformatie {
    

    public Patientinformatie(Stage stage, Scene sceneSearch) throws SQLException {
          
        GridPane gridPanePatient = new GridPane();
               
        Database db = new Database();    
        
        String id = db.getIDPatient();
        
        //gridPanePatient.setGridLinesVisible(true);
        
        Scene scenePatient = new Scene(gridPanePatient, 800, 500);
        
        stage.setScene(scenePatient);

        // Set Paddings
        gridPanePatient.setPadding(new Insets(10, 10, 10, 10));

        // Set the horizontal gap between columns
        gridPanePatient.setHgap(10);

        // Set the vertical gap between rows
        gridPanePatient.setVgap(10);
        
        //Aligment
        gridPanePatient.setAlignment(Pos.TOP_LEFT);
        
        
        // Labels en TextFields
        
        //Voornaam
        Label vollenaamLabel = new Label("Volle naam");
        vollenaamLabel.setPrefHeight(2);
        GridPane.setConstraints(vollenaamLabel, 0, 0);
        
        gridPanePatient.getChildren().add(vollenaamLabel);
        
        
        TextField vollenaamField = new TextField();
        vollenaamField.setPrefHeight(2);
        vollenaamField.setText(db.GetPatient());
        gridPanePatient.add(vollenaamField, 2, 0);
        
        //Achternaam
         Label achternaamLabel = new Label("Achternaam");
        achternaamLabel.setPrefHeight(2);
        GridPane.setConstraints(achternaamLabel, 0, 1);
        gridPanePatient.getChildren().add(achternaamLabel);
        
        
        TextField achternaamField = new TextField();
        achternaamField.setPrefHeight(2);
        achternaamField.setText(db.GetPatientSur());
        gridPanePatient.add(achternaamField, 2, 1);
        
        //Geboortedatum
        Label geboorteLabel = new Label("geboortedatum");
        geboorteLabel.setPrefHeight(2);
        geboorteLabel.setMinWidth(100);
        GridPane.setConstraints(geboorteLabel, 0, 2);
        gridPanePatient.getChildren().add(geboorteLabel);
        
        TextField geboorteField = new TextField();
        geboorteField.setPrefHeight(2);
        gridPanePatient.add(geboorteField, 2, 2);       
        geboorteField.setText(db.GetGeboorte());
        
        //Adres
        Label adresLabel = new Label("Adres");
        adresLabel.setPrefHeight(2);
        GridPane.setConstraints(adresLabel, 0, 3);
        gridPanePatient.getChildren().add(adresLabel);
        
        TextField adresField = new TextField();
        adresField.setPrefHeight(2);
        gridPanePatient.add(adresField, 2, 3);
        adresField.setText(db.GetAdres());
       
        //Telefoonnummer
        Label telefoonLabel = new Label("Telefoonnummer");
        telefoonLabel.setPrefHeight(2);
        GridPane.setConstraints(telefoonLabel, 0, 4);
        gridPanePatient.getChildren().add(telefoonLabel);
        
        TextField telefoonField = new TextField();
        telefoonField.setPrefHeight(2);
        gridPanePatient.add(telefoonField, 2, 4);
        telefoonField.setText(db.GetAdres());
        telefoonField.setText(db.GetTelefoon());
        
        //Huisarts
        Label huisartsLabel = new Label("Huisarts");
        huisartsLabel.setPrefHeight(2);
        GridPane.setConstraints(huisartsLabel, 0, 5);
        gridPanePatient.getChildren().add(huisartsLabel);
        
        TextField HuisartsField = new TextField();
        HuisartsField.setPrefHeight(2);
        gridPanePatient.add(HuisartsField, 2, 5);
        HuisartsField.setText(db.GetAdres());
        HuisartsField.setText(db.GetHuisarts());
        
        //Add Afspraak Button
        Button afspraakButton = new Button("Afspraken");
        afspraakButton.setPrefHeight(40);
        afspraakButton.setDefaultButton(true);
        afspraakButton.setMaxWidth(1000);
        gridPanePatient.add(afspraakButton, 29, 21);
        GridPane.setMargin(afspraakButton, new Insets(2, 0, 2, 0));
        
        //Logout Button
        Button terugButton = new Button("Terug");
        terugButton.setPrefHeight(40);
        terugButton.setDefaultButton(true);
        terugButton.setMaxWidth(1000);
        gridPanePatient.add(terugButton, 29, 22);
        GridPane.setMargin(terugButton, new Insets(2, 0, 2, 0));
        
        //Delete Button
         Button deleteButton = new Button("Verwijder");
        deleteButton.setPrefHeight(40);
        deleteButton.setDefaultButton(true);
        deleteButton.setMaxWidth(1000);
        gridPanePatient.add(deleteButton, 30, 21);
        GridPane.setMargin(deleteButton, new Insets(2, 0, 2, 0));
        
        //Button Delete
        deleteButton.setOnAction(e -> {
            db.PatientVerwijderen(id);
            stage.setScene(sceneSearch);
        });
        
        //Change Button
         Button changeButton = new Button("aanpassen");
        changeButton.setPrefHeight(40);
        changeButton.setDefaultButton(true);
        changeButton.setMaxWidth(1000);
        gridPanePatient.add(changeButton, 30, 22);
        GridPane.setMargin(changeButton, new Insets(2, 0, 2, 0));
        
                
        changeButton.setOnAction(e -> {
            // Waardes omzetten naar variabele
            String sVoornaam = vollenaamField.getText();
            String sAdres = adresField.getText();
            String sTelefoonnummer = telefoonField.getText();
            String sHuisarts = HuisartsField.getText();
            String sGeboorte = geboorteField.getText();
            String sAchternaam = achternaamField.getText();
            
            db.patientUpdaten(id, sVoornaam, sAchternaam, sAdres, sGeboorte, sTelefoonnummer, sHuisarts);
        });
        
        
               

                
        
        terugButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneSearch);
            }
        });
        
	        
	
        }
}
        
