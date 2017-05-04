/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 * The type Record. Stores record id and reference name.
 *
 * @author jwolf
 */
public class Record {

    public String ID;
    public String Name;

    /**
     * Instantiates a new Record.
     *
     * @param ID   the id
     * @param Name the name
     */
    public Record(String ID, String Name) {
        this.ID = ID;
        this.Name = Name;
    }

    /**
     * To string string.
     *
     * @return the the name
     */
    public String toString() {
        return Name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getID() {
        return ID;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return Name;
    }
}
