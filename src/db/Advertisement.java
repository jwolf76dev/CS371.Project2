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
public class Advertisement {
    private int ID;
    private String userID;
    private String moderatorID;
    private String statusID;
    private String title;
    private String details;
    private String date;
    private String price;
    
    public Advertisement(int ID, String userID, String moderatorID, String statusID,
            String title, String details, String date, String price) {
        this.ID=ID;
        this.userID=userID;
        this.moderatorID=moderatorID;
        this.statusID=statusID;
        this.title=title;
        this.details=details;
        this.date=date;
        this.price=price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getModeratorID() {
        return moderatorID;
    }

    public void setModeratorID(String moderatorID) {
        this.moderatorID = moderatorID;
    }

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    Object[] toArray(){
        return new Object[]{ID, userID, moderatorID, statusID, title, details, date, price};
    }
}
