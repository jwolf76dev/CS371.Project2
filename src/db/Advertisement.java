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
    private String title;
    private String details;
    private float price;
    private String categoryID;
    private String userID;
    private String statusID;
    private String moderatorID;
    private String date;
    
    public Advertisement(String title, String details, float price, String date) {
        this.title=title;
        this.details=details;
        this.price=price;
        this.date=date;
    }
    
    public Advertisement (int ID, String title, String details, float price,
            String statusID, String date) {
        this.ID=ID;
        this.title=title;
        this.details=details;
        this.price=price;
        this.statusID=statusID;
        this.date=date;
    }

    public Advertisement(int ID, String title, String details, float price,
            String categoryID, String userID, String date) {
        this.ID = ID;
        this.title = title;
        this.details = details;
        this.price = price;
        this.categoryID = categoryID;
        this.userID = userID;
        this.date = date;
    }

    public Advertisement(int ID, String title, String details,
            float price, String categoryID, String userID, String statusID,
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

    public Advertisement() {    }
    
    
    public int getID() {
        return ID;
    }

    public Advertisement setID(int ID) {
        this.ID = ID;
        return this;
    }

    public String getUserID() {
        return userID;
    }

    public Advertisement setUserID(String userID) {
        this.userID = userID;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Advertisement setDate(String date) {
        this.date = date;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Advertisement setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public Advertisement setDetails(String details) {
        this.details = details;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public Advertisement setPrice(float price) {
        this.price = price;
        return this;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public Advertisement setCategoryID(String categoryID) {
        this.categoryID = categoryID;
        return this;
    }

    
    public String getStatusID() {
        return statusID;
    }

    public Advertisement setStatusID(String statusID) {
        this.statusID = statusID;
        return this;
    }

    public String getModeratorID() {
        return moderatorID;
    }

    public Advertisement setModeratorID(String moderatorID) {
        this.moderatorID = moderatorID;
        return this;
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
