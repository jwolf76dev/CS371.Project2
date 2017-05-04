package Utilities;

import db.Record;
import db.Advertisement;
import java.util.LinkedList;
import javax.swing.JComboBox;

/**
 * @author luke on 4/29/2017.
 *
 * Utility functions to assist in processing data sent to queries
 */
public class Utilities {

    /**
     * Returns the numeric equivalent of a string for calculating
     * different date ranges.
     *
     * @param text  string with period of time
     * @return int  numeric equivalence of period of time in months
     */
    public static int getMonth(String text) {
        int month;
        switch (text) {
            case "Any Date":
                month = -1;
                break;
            case "This Month":
                month = 0;
                break;
            case "Last 3 Months":
                month = 3;
                break;

            case "Last 6 Months":
                month = 6;
                break;

            case "Last 12 Months":
                month = 12;
                break;
            default:
                month = -1;

        }
        return month;
    }

    /**
     * Validates db record category with available categories.
     *
     * @param categories    the available category Records
     * @param categoryID    the db record category id
     * @return category     the db Record category
     * @throws Exception    db record category not found in available categories
     */
    public static Record findCategory(JComboBox<Record> categories, String categoryID) throws Exception {
        for (int x = 0; x < categories.getItemCount(); x++) {
            Record category = (Record) categories.getItemAt(x);
            if (category.getID().equals(categoryID)) {
                return category;
            }
        }
        throw new Exception("Category not found");
    }

    /**
     * Validates db record status with available statuses.
     *
     * @param status        the available status Records
     * @param statusID      the db record status id
     * @return category     the db Record status
     * @throws Exception    db record status not found in available statuses
     */
    public static Record findStatus(JComboBox<Record> status, String statusID) throws Exception {
        for (int x = 0; x < status.getItemCount(); x++) {
            Record category = (Record) status.getItemAt(x);
            if (category.getID().equals(statusID)) {
                return category;
            }
        }
        throw new Exception("Status not found");
    }
}