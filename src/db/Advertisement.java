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
    private String ID;
    private String userID;
    private String date;
    private String title;
    private String details;
    private String price;
    private String statusID;
    private String moderatorID;
    
    public Advertisement(String title, String details, String price, String date) {
        this.title=title;
        this.details=details;
        this.price=price;
        this.date=date;
    }
    
    public Advertisement(String ID, String userID, String date, String title,
            String details, String price, String statusID, String moderatorID) {
        this.ID=ID;
        this.userID=userID;
        this.date=date;
        this.title=title;
        this.details=details;
        this.price=price;
        this.statusID=statusID;
        this.moderatorID=moderatorID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    public String getModeratorID() {
        return moderatorID;
    }

    public void setModeratorID(String moderatorID) {
        this.moderatorID = moderatorID;
    }

    Object[] toArray(){
        return new Object[]{ID, userID, date, title, details, price, statusID, moderatorID};
    }
}
