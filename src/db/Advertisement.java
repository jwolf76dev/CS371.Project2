/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 * The type Advertisement.
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

    /**
     * Instantiates a new Advertisement.
     *
     * @param title   the title
     * @param details the details
     * @param price   the price
     * @param date    the date
     */
    public Advertisement(String title, String details, float price, String date) {
        this.title=title;
        this.details=details;
        this.price=price;
        this.date=date;
    }

    /**
     * Instantiates a new Advertisement.
     *
     * @param ID       the id
     * @param title    the title
     * @param details  the details
     * @param price    the price
     * @param statusID the status id
     * @param date     the date
     */
    public Advertisement (int ID, String title, String details, float price,
            String statusID, String date) {
        this.ID=ID;
        this.title=title;
        this.details=details;
        this.price=price;
        this.statusID=statusID;
        this.date=date;
    }

    /**
     * Instantiates a new Advertisement.
     *
     * @param ID         the id
     * @param title      the title
     * @param details    the details
     * @param price      the price
     * @param categoryID the category id
     * @param userID     the user id
     * @param date       the date
     */
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

    /**
     * Instantiates a new Advertisement.
     *
     * @param ID         the id
     * @param title      the title
     * @param details    the details
     * @param price      the price
     * @param categoryID the category id
     * @param userID     the user id
     * @param statusID   the status id
     * @param date       the date
     */
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

    /**
     * Instantiates a new Advertisement.
     */
    public Advertisement() {    }


    /**
     * Gets id.
     *
     * @return the id
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets id.
     *
     * @param ID the id
     * @return the id
     */
    public Advertisement setID(int ID) {
        this.ID = ID;
        return this;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets user id.
     *
     * @param userID the user id
     * @return the user id
     */
    public Advertisement setUserID(String userID) {
        this.userID = userID;
        return this;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     * @return the date
     */
    public Advertisement setDate(String date) {
        this.date = date;
        return this;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     * @return the title
     */
    public Advertisement setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Gets details.
     *
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets details.
     *
     * @param details the details
     * @return the details
     */
    public Advertisement setDetails(String details) {
        this.details = details;
        return this;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     * @return the price
     */
    public Advertisement setPrice(float price) {
        this.price = price;
        return this;
    }

    /**
     * Gets category id.
     *
     * @return the category id
     */
    public String getCategoryID() {
        return categoryID;
    }

    /**
     * Sets category id.
     *
     * @param categoryID the category id
     * @return the category id
     */
    public Advertisement setCategoryID(String categoryID) {
        this.categoryID = categoryID;
        return this;
    }


    /**
     * Gets status id.
     *
     * @return the status id
     */
    public String getStatusID() {
        return statusID;
    }

    /**
     * Sets status id.
     *
     * @param statusID the status id
     * @return the status id
     */
    public Advertisement setStatusID(String statusID) {
        this.statusID = statusID;
        return this;
    }

    /**
     * Gets moderator id.
     *
     * @return the moderator id
     */
    public String getModeratorID() {
        return moderatorID;
    }

    /**
     * Sets moderator id.
     *
     * @param moderatorID the moderator id
     * @return the moderator id
     */
    public Advertisement setModeratorID(String moderatorID) {
        this.moderatorID = moderatorID;
        return this;
    }

    /**
     * Active ads to array object [ ].
     *
     * @return the object [ ]
     */
    Object[] activeAdsToArray(){
        return new Object[]{title, details, price, date};
    }

    /**
     * User ads to array object [ ].
     *
     * @return the object [ ]
     */
    Object[] userAdsToArray() {
        return new Object[]{ID, title, details, price, statusID, date};
    }

    /**
     * Unclaimed ads to array object [ ].
     *
     * @return the object [ ]
     */
    Object[] unclaimedAdsToArray() {
        return new Object[]{ID, title, details, price, categoryID, userID, date};
    }

    /**
     * Moderator ads to array object [ ].
     *
     * @return the object [ ]
     */
    Object[] moderatorAdsToArray() {
        return new Object[]{ID, title, details, price, categoryID, userID, statusID, date};
    }

    /**
     * All ads to array object [ ].
     *
     * @return the object [ ]
     */
    Object[] allAdsToArray(){
        return new Object[]{ID, userID, title, details, price, categoryID, statusID, moderatorID, date};
    }
}
