/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;
import db.*;
import javax.swing.JFrame;
/**
 *
 * @author jwolf
 */
public class Main {

    public static void main(String[] args){
        DBManager DB=new DBManager();
        try{
            
            DB.connect("UMKCDatabase","Umkcdatabase","cs-371.cdzwjr1xplmp.us-east-1.rds.amazonaws.com","3306","Cs_371");
            JFrame LoginScreen=new LoginScreen(DB);
            LoginScreen.setVisible(true);
        }
        catch(Exception e){
            
        }
    }
}
