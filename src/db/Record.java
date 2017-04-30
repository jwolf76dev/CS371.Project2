/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author jwolf
 */
public class Record {

    public String ID;
    public String Name;

    public Record(String ID, String Name) {
        this.ID = ID;
        this.Name = Name;
    }
    
    public String toString() {
        return Name;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }
}
