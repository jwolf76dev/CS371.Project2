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
     * Returns a category record
     * @param categories
     * @param categoryID
     * @return
     * @throws Exception
     */
/*    public static Record findCategory(JComboBox<Record> categories, String categoryID) throws Exception {
        for (int x = 0; x < categories.getItemCount(); x++) {
            Record category = (Record) categories.getItemAt(x);
            if (category.getID().equals(categoryID)) {
                return category;
            }
        }
        throw new Exception("Category not found");
    }
    
    public static Record findStatus(JComboBox<Record> status, String statusID) throws Exception {
        for (int x = 0; x < status.getItemCount(); x++) {
            Record category = (Record) status.getItemAt(x);
            if (category.getID().equals(statusID)) {
                return category;
            }
        }
        throw new Exception("Status not found");
    }*/
}