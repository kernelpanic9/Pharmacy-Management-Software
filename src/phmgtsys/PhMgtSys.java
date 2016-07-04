/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phmgtsys;

import database.DatabaseHelper;
import phmgtsys.*;



/**
 *
 * @author TREMEX
 */
public class PhMgtSys {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try{
            //DatabaseHelper db = new DatabaseHelper();
            //System.out.println( db.createConnection() );
            
            Login login = new Login();
            login.setResizable(false);
            login.setVisible(true);
            
            
        }
        catch( Exception e ){
            System.out.println( e.getMessage() );
            Notification note = new Notification();
            note.setMessage("Database connection failed.");
            System.out.println( e.getMessage() );
        }
        
    }
    
}
