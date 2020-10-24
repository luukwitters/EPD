
package beroepsproduct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;


public class Database {
    Connection con;
    Statement stm;
    ResultSet rset;
    
    //Een lijst van Strings en Doubles voor de ophaal functies van data uit de database naar het programma.
    
    String Voornaam;
    String Achternaam;
    String Adres;
    String Telefoonnummer;
    String Geboortedatum;
    String Huisarts;
    
    // Constructor van je class om de connectie met de database te maken
    public Database() {
        try {
            // Query maken
            con = DriverManager.getConnection("jdbc:mysql://localhost:3309/zorg?user=root&password=password");
            // Van de query een statement maken die naar de database kan
            stm = con.createStatement();
        } catch (SQLException error) { 
            // error in de log zetten
            System.out.println(error);
        }

    }
    
    //Nieuwe patient toevoegen
    public boolean PatientToevoegen(String sVoornaam, String sAchternaam, String sGeboortedatum, String sTelefoonnummer, String sAdres, 
            String sHuisarts) {
        // Query maken
        
        String sQuery = "INSERT INTO patienten(Voornaam, Achternaam, Adres, Telefoonnummer, Geboortedatum, Huisarts) VALUES ('" + sVoornaam + "','" + sAchternaam + "','" + sAdres + "','" + sTelefoonnummer + "','" + 
                        sGeboortedatum + "','" + sHuisarts +  "')";
        // Test Query
        System.out.println(sQuery);
        try {
            // Query omzetten naar stm
            stm = con.createStatement();
            // Kijken of de query wordt uitgevoerd
            if(stm.execute(sQuery)){     
                
                System.out.println("Query is naar de database gegaan");
                // system print als het goed is gegaan
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("PatientToevoegen functie in de Database Class");
            return false;
        }
        // Failsafe
        return false;
    }

    public boolean PatientVerwijderen(String sPatientID){
        String sSquery = "DELETE FROM Patienten WHERE Patiënt_ID = "+sPatientID;
        
        System.out.println(sSquery);
        try {
            // Query omzetten naar stm
            stm = con.createStatement();
            // Kijken of de query wordt uitgevoerd
            if(stm.execute(sSquery)){    
                System.out.println("Query is naar de database gegaan");
                // system print als het goed is gegaan
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("PatientToevoegen functie in de Database Class");
            return false;
        }
        
        //failsafe
        return false;
    }
    
        public boolean patientUpdaten(String sPatientID, String sVoornaam, String sAchternaam, String sAdres, String sGeboorte, String sTelefoonnummer, String sHuisarts){
        String sSquery = "UPDATE Patienten SET Adres = '"+sAdres+"', Voornaam = '"+sVoornaam+"',Achternaam = '"+sAchternaam+"',Geboortedatum = '"+sGeboorte+"',Huisarts = '"+sHuisarts+"', Telefoonnummer = '"+sTelefoonnummer+"' WHERE Patiënt_ID = "+sPatientID;
        
        System.out.println(sSquery);
        try {
            // Eerst proberen om om te zetten naar een statement
            stm = con.createStatement();
            // Kijken of de query wordt uitgevoerd
            if(stm.execute(sSquery)){    
                // Vind het altijd fijn om te checken   
                System.out.println("Query is naar de database gegaan");
                // Teruggeven dat het goed is gegaan
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("PatientToevoegen functie in de Database Class");
            return false;
        }
        
        return false;
    }
    
    
    public boolean SPatient(String sIDzoek) throws SQLException{
        
        String sQuery = "SELECT * FROM patienten WHERE Patiënt_ID ='" +sIDzoek+ "'";
        
        boolean zoek = false;
         
        try{
            stm.execute(sQuery);
            
            ResultSet rs = stm.getResultSet();
            
                if(!rs.next()){
                    
                    System.out.println("Bestaat Niet"); 
                    
                } else {
                    zoek = true;
                    
                    //set het id zodat het later opgehaald kan worden
                    Preferences pref;
                    pref = Preferences.userNodeForPackage(Database.class);
                    pref.put("GetPatient",sIDzoek);
                }
        }catch (SQLException ex) {
         Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    return zoek;
    }
    
  public String GetPatient() {
        Preferences pref;
        pref = Preferences.userNodeForPackage(Database.class);
        String sIDzoek = pref.get("GetPatient", "");
        String sresult = null;
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            if (stmt.execute("SELECT * FROM patienten Where Patiënt_ID  ='" +sIDzoek+ "'")) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    //String sresult = rs.getString("Voornaam") + " " + rs.getString("Achternaam"));
                    String naam = rs.getString("Voornaam");
                    return naam;
                }
            }
            return sresult;
        } catch (SQLException e) {

            return null;
        }
  
}
  
  public String GetPatientSur() {
        Preferences pref;
        pref = Preferences.userNodeForPackage(Database.class);
        String sIDzoek = pref.get("GetPatient", "");
        String sresult = null;
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            if (stmt.execute("SELECT * FROM patienten Where Patiënt_ID  ='" +sIDzoek+ "'")) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    //String sresult = rs.getString("Voornaam") + " " + rs.getString("Achternaam"));
                    String naam = rs.getString("Achternaam");
                    return naam;
                }
            }
            return sresult;
        } catch (SQLException e) {

            return null;
        }
  
}
  
  public String getIDPatient(){
        Preferences pref;
        pref = Preferences.userNodeForPackage(Database.class);
        String sIDzoek = pref.get("GetPatient", "");
        
        return sIDzoek;
  }
  
  public String GetAdres() {
        Preferences pref;
        pref = Preferences.userNodeForPackage(Database.class);
        String sIDzoek = pref.get("GetPatient", "");

        
        String sresult = null;
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            if (stmt.execute("SELECT * FROM patienten Where Patiënt_ID  ='" +sIDzoek+ "'" )) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    sresult = rs.getString("Adres");
                }
            }
            return sresult;
        } catch (SQLException e) {

            return null;
        }
  
}
  
    public String GetGeboorte() {
        Preferences pref;
        pref = Preferences.userNodeForPackage(Database.class);
        String sIDzoek = pref.get("GetPatient", "");
        String sresult = null;
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            if (stmt.execute("SELECT * FROM patienten Where Patiënt_ID  ='" +sIDzoek+ "'")) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    sresult = rs.getString("Geboortedatum");
                }
            }
            return sresult;
        } catch (SQLException e) {

            return null;
        }
  
}
    
  public String GetTelefoon() {
        Preferences pref;
        pref = Preferences.userNodeForPackage(Database.class);
        String sIDzoek = pref.get("GetPatient", "");
                
        String sresult = null;
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            if (stmt.execute("SELECT * FROM patienten Where Patiënt_ID  ='" +sIDzoek+ "'" )) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    sresult = rs.getString("Telefoonnummer");
                }
            }
            return sresult;
        } catch (SQLException e) {

            return null;
        }
  
}
  
  public String GetHuisarts() {
        Preferences pref;
        pref = Preferences.userNodeForPackage(Database.class);
        String sIDzoek = pref.get("GetPatient", "");

        
        String sresult = null;
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            if (stmt.execute("SELECT * FROM patienten Where Patiënt_ID  ='" +sIDzoek+ "'" )) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    sresult = rs.getString("Huisarts");
                }
            }
            return sresult;
        } catch (SQLException e) {

            return null;
        }
  
}
  
}