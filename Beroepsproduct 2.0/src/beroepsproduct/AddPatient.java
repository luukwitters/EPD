/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beroepsproduct;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author moerl
 */


public class AddPatient {
    
    public AddPatient(Stage stage, Scene sceneSearch){
        Database db = new Database();
               
        GridPane gridPaneAdd = new GridPane();
        
        Scene sceneAdd = new Scene(gridPaneAdd, 800, 500);
        
        stage.setScene(sceneAdd);
        
        
        // Position GridPane
        gridPaneAdd.setAlignment(Pos.CENTER);
        
        // Set Paddings
        gridPaneAdd.setPadding(new Insets(40, 40, 40, 40));

        // Set horizontal gap
        gridPaneAdd.setHgap(10);

        // Set vertical gap
        gridPaneAdd.setVgap(10);
        
        // TextFields toevoegen en de text in de TextFields aanpassen:
                       
        TextField txtVoornaam = new TextField();
        TextField txtAchternaam = new TextField();
        TextField txtAdres = new TextField();
        TextField txtTelefoonnummer = new TextField();
        TextField txtGeboortedatum = new TextField();
        TextField txtHuisarts = new TextField();
                
        // Labels toevoegen en de text in de labels aanpassen:
        
        Label lblVoornaam = new Label("Voornaam: ");
        Label lblAchternaam = new Label("Achternaam: ");
        Label lblAdres = new Label("Adres: ");
        Label lblTelefoonnummer = new Label("Telefoonnummer: ");
        Label lblGeboortedatum = new Label("Geboortedatum: ");
        Label lblHuisarts = new Label("Huisarts: ");
        Label legeInvoer = new Label ("");
                
        // Buttons toevoegen en de text op de Buttons aanpassen:
        
        Button terugButton = new Button("Terug");
        Button toevoegenButton = new Button("Toevoegen");
        Button deleteButton = new Button ("Delete");
        
        
        //Button Terug
        
            terugButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneSearch);
            }
        });
        toevoegenButton.setOnAction((ActionEvent e) -> {
        String invoerVoornaam = txtVoornaam.getText();
        String invoerAchternaam = txtAchternaam.getText();
        String invoerGeboorte = txtGeboortedatum.getText();
        String invoerTelefoon = txtTelefoonnummer.getText();
        String invoerAdres = txtAdres.getText();
        String invoerHuisarts = txtHuisarts.getText();
        
         if(invoerVoornaam.equals("") || invoerAchternaam.equals("") || invoerGeboorte.equals("")  ||  invoerHuisarts.equals("")|| invoerTelefoon.equals("") || invoerAdres.equals("")) {
        legeInvoer.setText("Vul alle gegevens in");
        legeInvoer.setTextFill(Color.RED);
        }
         else {

             db.PatientToevoegen(invoerVoornaam, invoerAchternaam, invoerGeboorte, invoerTelefoon, invoerAdres, invoerHuisarts);
             legeInvoer.setText("De patiënt is opgeslagen");
             legeInvoer.setTextFill(Color.GREEN);

                }


        });
            
        

        
        //Alle Buttons toevoegen aan de GridPane
        
        gridPaneAdd.add(terugButton, 1, 9);
        gridPaneAdd.add(toevoegenButton, 1, 8);
        
      
        
        //Alle Labels toevoegen aan de GridPane
        
        gridPaneAdd.add(lblVoornaam, 0, 1);
        gridPaneAdd.add(lblAchternaam, 0, 2);
        gridPaneAdd.add(lblAdres, 0, 3);
        gridPaneAdd.add(lblTelefoonnummer, 0, 5);
        gridPaneAdd.add(lblGeboortedatum, 0, 4);
        gridPaneAdd.add(lblHuisarts, 0, 6);
        gridPaneAdd.add(legeInvoer, 0, 7);
        
        //Alle TextFields toevoegen aan de GridPane
        
        gridPaneAdd.add(txtVoornaam, 1, 1);
        gridPaneAdd.add(txtAchternaam, 1, 2);
        gridPaneAdd.add(txtAdres, 1, 3);
        gridPaneAdd.add(txtTelefoonnummer, 1, 5);
        gridPaneAdd.add(txtGeboortedatum, 1, 4);
        gridPaneAdd.add(txtHuisarts, 1, 6);
                
    }
    
}