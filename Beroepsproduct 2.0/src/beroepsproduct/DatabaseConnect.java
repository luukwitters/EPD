
package beroepsproduct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseConnect {
    Connection con;
    Statement stm;
    ResultSet rset;
    
    // Constructor
    
    public DatabaseConnect() throws SQLException{
        
        //Verbinding maken met MySQL
        con = DriverManager.getConnection("jjdbc:mysql://localhost:3309/zorg?user='root'&password='password'");
        
        System.out.println(con);
        
           
        
    }
        public boolean PatientToevoegen(String sVoornaam, String sAchternaam, String sGeboorteDatum, String sAdres, String sHuisarts){
        
        
        // Query aanmaken voor het toevoegen van een patient met de gegevens naam, achternaam, geboortedatum, adres en aandoening.
        
        String sQuery = "INSERT INTO patienten VALUES ('" + sVoornaam + "','" + sAchternaam + "','" +
                        sGeboorteDatum + "','" + sAdres + ",'" + sHuisarts+"')";
        System.out.println(sQuery);
        try {
            stm = con.createStatement();
            if(stm.execute(sQuery)){
                System.out.println("Gelukt!");
            }else System.out.println("Mislukt!");
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
}