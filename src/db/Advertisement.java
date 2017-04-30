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
    private String title;
    private String details;
    private String price;
    private String categoryID;
    private String userID;
    private String statusID;
    private String moderatorID;
    private String date;
    
    public Advertisement(String title, String details, String price, String date) {
        this.title=title;
        this.details=details;
        this.price=price;
        this.date=date;
    }
    
    public Advertisement (String ID, String title, String details, String price,
            String statusID, String date) {
        this.ID=ID;
        this.title=title;
        this.details=details;
        this.price=price;
        this.statusID=statusID;
        this.date=date;
    }

    public Advertisement(String ID, String title, String details, String price,
            String categoryID, String userID, String date) {
        this.ID = ID;
        this.title = title;
        this.details = details;
        this.price = price;
        this.categoryID = categoryID;
        this.userID = userID;
        this.date = date;
    }

    public Advertisement(String ID, String title, String details,
            String price, String categoryID, String userID, String statusID,
            String date) {
        this.ID=ID;
        this.title=title;
        this.details=details;
        this.price=price;
        this.categoryID=categoryID;
        this.userID=userID;
        this.statusID=statusID;
        this.date=date;
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

    Object[] activeAdsToArray(){
        return new Object[]{title, details, price, date};
    }
    
    Object[] userAdsToArray() {
        return new Object[]{ID, title, details, price, statusID, date};
    }
    
    Object[] unclaimedAdsToArray() {
        return new Object[]{ID, title, details, price, categoryID, userID, date};
    }
    
    Object[] moderatorAdsToArray() {
        return new Object[]{ID, title, details, price, categoryID, userID, statusID, date};
    }
    
    Object[] allAdsToArray(){
        return new Object[]{ID, userID, title, details, price, categoryID, statusID, moderatorID, date};
    }
}
