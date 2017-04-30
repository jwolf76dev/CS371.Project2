package Utilities;

/**
 * Created by luke_ on 4/29/2017.
 */
public class MonthUtils {
    public static int getMonth(String text){
        int month;
        switch (text){
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

            case "Last Year":
                month = 12;
            break;
            default: month = -1;

        }
        return month;
    }
}
