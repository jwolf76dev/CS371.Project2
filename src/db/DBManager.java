/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import Utilities.Utilities;
import com.mysql.jdbc.StringUtils;

import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;

/**
 * The type Db manager. Manages all interactions between the ui and database.
 *
 * @author lsimmons
 * @author jwolf
 */
public class DBManager {

    /**
     * The database connection.
     */
    Connection connection;

    /**
     * Connect to the database.
     *
     * @param userName   the database user name
     * @param password   the database password
     * @param serverName the database server name
     * @param portNumber the database port number
     * @param dbName     the database name
     * @throws SQLException           the sql exception
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     */
    public void connect(String userName, String password, String serverName, String portNumber, String dbName)
            throws SQLException, InstantiationException, IllegalAccessException {
        System.out.println("Loading driver...");

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);

        conn = DriverManager.getConnection(
                "jdbc:mysql://"
                        + serverName
                        + ":" + portNumber + "/" + dbName,
                connectionProps);

        System.out.println("Connected to database");
        this.connection = conn;
    }

    /**
     * Builds the check user query.
     *
     * @param userID    the user id
     * @return User from the database
     */
    public boolean checkUser(String userID) {
        String query = "select * FROM Users WHERE userID=?";
        return checkUser(query, userID);
    }

    /**
     * Builds the check moderator query.
     *
     * @param userID    the user id
     * @return Moderator from the Database
     */
    public boolean checkModerator(String userID) {
        String query = "select * FROM Moderators WHERE userID=?";
        return checkUser(query, userID);
    }

    /**
     * Validates the user.
     *
     * @param check employee query
     * @param userID
     * @return boolean  true: the user is valid. false: the user does not exist
     */
    
    private boolean checkUser(String query, String userID) {
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, userID); //binding the parameter with the given string
            ResultSet rs = stmt.executeQuery();
            int count = getResultSetSize(rs);
            if (count == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    /**
     * Gets categories.
     *
     * @return all given categories that an advertisement can be. This will be used to populate the dropdown table.
     */
    public LinkedList<Record> getCategories() {
        PreparedStatement stmt = null;
        LinkedList<Record> categoriesList = new LinkedList<>();
        String query = "Select * From Categories";
        try {
            stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                categoriesList.add(new Record(rs.getString("categoryID"), rs.getString("categoryName")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoriesList;
    }

    /**
     * Gets statuses.
     *
     * @return all given statuses that an advertisement can be.
     */
    public LinkedList<Record> getStatuses() {
        PreparedStatement stmt = null;
        LinkedList<Record> statusList = new LinkedList<>();
        String query = "Select * From Statuses";
        try {
            stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                statusList.add(new Record(rs.getString("statusID"), rs.getString("statusName")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusList;
    }

    /**
     * Gets ad by id.
     *
     * @param id    db id of an advertisement
     * @return Advertisement from db with that given id
     */
    public Advertisement getAdByID(int id) {
        PreparedStatement stmt = null;
        Advertisement result = new Advertisement();
        String query = "Select advertisementID, advertisementTitle, "
                + "advertisementDetails, price, categoryID, statusID "
                + "From Advertisements Where advertisementID = ?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            result.setID(rs.getInt("advertisementID"))
                    .setTitle(rs.getString("advertisementTitle"))
                    .setDetails(rs.getString("advertisementDetails"))
                    .setPrice(rs.getFloat("price"))
                    .setCategoryID(rs.getString("categoryID"))
                    .setStatusID(rs.getString("statusID"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Gets active ads Object[][].
     *
     * @param rs    Result set containing Active ad information.
     * @return 2D Object used to populate the User all ads table.
     * @throws SQLException
     */
    private Object[][] getActiveAds(ResultSet rs) throws SQLException {

        int count = getResultSetSize(rs);
        rs.beforeFirst();
        Object[][] result = new Object[count][4];
        int index = 0;
        while (rs.next()) {
            result[index][0] = rs.getString("advertisementTitle");
            result[index][1] = rs.getString("advertisementDetails");
            result[index][2] = rs.getFloat("price");
            result[index][3] = rs.getString("advertisementDate");
            index++;
        }
        return result;

    }

    /**
     * Gets unclaimed ads Object[][].
     *
     * @param rs    Result set containing Active ad information.
     * @return 2D Object used to populate the Moderator ads table.
     * @throws SQLException
     */
    private Object[][] getUnclaimedAds(ResultSet rs) throws SQLException {

        int count = getResultSetSize(rs);
        rs.beforeFirst();
        Object[][] result = new Object[count][6];
        int index = 0;
        while (rs.next()) {
            result[index][0] = rs.getString("advertisementID");
            result[index][1] = rs.getString("advertisementTitle");
            result[index][2] = rs.getString("advertisementDetails");
            result[index][3] = rs.getFloat("price");
            result[index][4] = rs.getString("advertisementDate");
            result[index][5] = rs.getString("userID");
            index++;
        }
        return result;

    }

    /**
     * Get all Users ads Object[][].
     *
     * @param userID    id of the user, used to find ads that belong to the user
     * @return 2D Object used to populate
     */
    public Object[][] getAllUsersAds(String userID) {
        PreparedStatement stmt = null;
        Object[][] results = new Object[][]{};

        String query = "SELECT A.advertisementID, A.advertisementTitle, A.advertisementDetails, "
                + "A.price, A.statusID, DATE(A.advertisementDateTime) advertisementDate "
                + "FROM Advertisements A "
                + "WHERE userID = ?";

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, userID);
            ResultSet rs = stmt.executeQuery();
            int count = getResultSetSize(rs);
            results = getUserAds(count, rs);
        } catch (Exception e) {
            e.printStackTrace();
            return results;
        }
        return results;
    }

    /**
     * Gets user ads Object[][].
     *
     * @param count size of the Result set
     * @param rs    Result set containing user ad information
     * @return 2D Object containing parsed ad information
     * @throws SQLException
     */
    private Object[][] getUserAds(int count, ResultSet rs) throws SQLException {
        Object[][] result = new Object[count][6];
        rs.beforeFirst();
        int index = 0;
        while (rs.next()) {
            int id = rs.getInt("advertisementID");
            String title = rs.getString("advertisementTitle");
            String details = rs.getString("advertisementDetails");
            float price = rs.getFloat("price");
            String status = rs.getString("statusID");
            String date = rs.getString("advertisementDate");

            Advertisement advertisement = new Advertisement(id, title, details, price, status, date);
            result[index++] = advertisement.userAdsToArray();
        };
        return result;
    }

    /**
     * Add advertisement boolean.
     *
     * @param advertisement object containing ad information
     * @return boolean      true: ad successfully created. false: ad was not added
     */
    public boolean addAdvertisement(Advertisement advertisement) {
        PreparedStatement stmt = null;

        String query = "INSERT INTO Advertisements (advertisementTitle, advertisementDetails, "
                + "advertisementDateTime, price, categoryID, userID, statusID) "
                + "VALUES (?,?,CURRENT_DATE(),?,?,?,?)";

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, advertisement.getTitle());
            stmt.setString(2, advertisement.getDetails());
            stmt.setFloat(3, advertisement.getPrice());
            stmt.setString(4, advertisement.getCategoryID());
            stmt.setString(5, advertisement.getUserID());
            stmt.setString(6, "PN");
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * User update advertisement boolean.
     *
     * @param advertisement object containing ad information to be added to the database
     * @return boolean      true: ad successfully updated. false: ad was not updated
     */
    public boolean userUpdateAdvertisement(Advertisement advertisement) {
        PreparedStatement stmt = null;

        String query = "UPDATE Advertisements "
                + "SET advertisementTitle= ?, advertisementDetails= ?, price= ?, "
                + "categoryID= ?, statusID= ? "
                + "WHERE advertisementID= ?";

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, advertisement.getTitle());
            stmt.setString(2, advertisement.getDetails());
            stmt.setFloat(3, advertisement.getPrice());
            stmt.setString(4, advertisement.getCategoryID());
            stmt.setString(5, "PN");
            stmt.setInt(6, advertisement.getID());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Moderator update advertisement boolean.
     *
     * @param advertisement object containing ad information to be updated
     * @return boolean      true: ad successfully updated. false: ad was not updated
     */
    public boolean moderatorUpdateAdvertisement(Advertisement advertisement) {
        PreparedStatement stmt = null;

        String query = "UPDATE Advertisements "
                + "SET categoryID= ?, statusID= ? "
                + "WHERE advertisementID= ?";

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, advertisement.getCategoryID());
            stmt.setString(2, advertisement.getStatusID());
            stmt.setInt(3, advertisement.getID());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Claim advertisement boolean.
     *
     * @param adID        ad to be claimed by the moderator
     * @param moderatorID moderator to claim to add
     * @return boolean    true: ad has been successfully claimed. false: ad has not been claimed
     */
    public boolean claimAdvertisement(int adID, String moderatorID) {
        PreparedStatement stmt = null;

        String query = "UPDATE Advertisements "
                + "SET moderatorID= ? "
                + "WHERE advertisementID= ?";

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, moderatorID);
            stmt.setInt(2, adID);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get all Moderators ads Object [][].
     *
     * @param userID    moderator id of the ads
     * @return 2D object containing ads owned by the given moderator
     */
    public Object[][] getAllModeratorsAds(String userID) {
        PreparedStatement stmt = null;
        Object[][] results = new Object[][]{};

        String query = "SELECT A.advertisementID, A.advertisementTitle, "
                + "A.advertisementDetails, A.price, A.categoryID, A.userID, "
                + "A.statusID, DATE(A.advertisementDateTime) advertisementDate "
                + "FROM Advertisements A "
                + "WHERE moderatorID = ?";

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, userID);
            ResultSet rs = stmt.executeQuery();
            int count = getResultSetSize(rs);
            results = getModeratorAds(count, rs);
        } catch (Exception e) {
            e.printStackTrace();
            return results;
        }
        return results;
    }

    /**
     * Get Moderator ads Object[][].
     *
     * @param count number of rows in the Result set
     * @param rs    Result set containing ad information
     * @return 2D object containing ads
     * @throws SQLException
     */
    private Object[][] getModeratorAds(int count, ResultSet rs) throws SQLException {
        Object[][] result = new Object[count][8];
        int index = 0;
        do {
            int id = rs.getInt("advertisementID");
            String title = rs.getString("advertisementTitle");
            String details = rs.getString("advertisementDetails");
            float price = rs.getFloat("price");
            String category = rs.getString("categoryID");
            String user = rs.getString("userID");
            String status = rs.getString("statusID");
            String date = rs.getString("advertisementDate");

            Advertisement advertisement = new Advertisement(id, title, details, price,
                    category, user, status, date);
            result[index++] = advertisement.moderatorAdsToArray();
        } while (rs.next());
        return result;
    }

    /**
     * Delete ad by id boolean.
     *
     * @param ID        id of ad to be deleted
     * @return boolean  true: ad was successfully deleted. false: ad was not deleted.
     */
    public boolean deleteAdByID(int ID) {
        PreparedStatement stmt = null;
        String query = "Delete From Advertisements Where advertisementID = ?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, ID);
            int delete = stmt.executeUpdate();
            if (delete == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Search unclaimed moderator ads Object[][].
     *
     * @param category      category of ad to be searched
     * @param period        time period of ad to be searched
     * @param searchText    string to search ads by
     * @return ads based on the search parameters
     */
    public Object[][] searchUnclaimedModeratorAds(String category, String period, String searchText) {
        PreparedStatement stmt = null;
        ResultSet rs;
        int month = Utilities.getMonth(period);
        boolean hasCategory = !category.equals("All");
        boolean hasPeriod = month != -1;
        boolean hasSearchText = !StringUtils.isNullOrEmpty(searchText);
        String[] searchArray = searchText.split(" ");
        String query = "Select advertisementID, advertisementTitle, advertisementDetails, price, "
                + "Date(advertisementDateTime) as advertisementDate, userID "
                + "From Advertisements "
                + "Where moderatorID IS NULL";
        try {
            stmt = connection.prepareStatement(query);

            // There is no good way to have a variable number of prepared statements.
            // We first check to see if specific values have been entered to search by.
            if (hasCategory || hasSearchText || hasPeriod) {
                if (hasCategory) {
                    query += " And categoryID = ?";
                }

                if (hasPeriod) {
                    query += " And advertisementDateTime > curdate() - INTERVAL (DAYOFMONTH(curdate()) -1) DAY - INTERVAL ? MONTH";
                }
                if (hasSearchText) {
                    String passInQuery = query;
                    query = buildSearchQuery(passInQuery, searchArray);
                }
                // At this point the query has been built now it is time to enter the sanitized values.
                // This is slightly redundant but necessary to check all possibilities.
                if (hasCategory) {
                    stmt = connection.prepareStatement(query);
                    stmt.setString(1, category);
                    if (hasPeriod) {
                        stmt.setInt(2, month);
                        if (hasSearchText) {
                            stmt = addSearchQueryValues(stmt, searchArray, 3);
                        }
                    }
                    if (hasSearchText) {
                        stmt = addSearchQueryValues(stmt, searchArray, 2);
                    }

                } else if (hasPeriod) {
                    stmt = connection.prepareStatement(query);
                    stmt.setInt(1, month);

                    if (hasSearchText) {
                        stmt = addSearchQueryValues(stmt, searchArray, 2);

                    }
                } else {
                    stmt = connection.prepareStatement(query);
                    stmt = addSearchQueryValues(stmt, searchArray, 1);
                }

            }
            rs = stmt.executeQuery();
            return getUnclaimedAds(rs);
        } catch (Exception e) {
            e.printStackTrace();
            return new Object[][]{};
        }
    }

    /**
     * Search active user ads Object[][].
     *
     * @param category      category of ad to be searched
     * @param period        time period of ad to be searched
     * @param searchText    string to search ads by
     * @return ads based on the search parameters
     */
    public Object[][] searchActiveUserAds(String category, String period, String searchText) {
        PreparedStatement stmt = null;
        ResultSet rs;
        int month = Utilities.getMonth(period);
        boolean hasCategory = !category.equals("All");
        boolean hasPeriod = month != -1;
        boolean hasSearchText = !StringUtils.isNullOrEmpty(searchText);
        String[] searchArray = searchText.split(" ");
        String query = "Select advertisementTitle, advertisementDetails, price, "
                + "Date(advertisementDateTime) as advertisementDate "
                + "From Advertisements "
                + "Where statusID = 'AC'";
        try {
            stmt = connection.prepareStatement(query);

            // There is no good way to have a variable number of prepared statements.
            // We first check to see if specific values have been entered to search by.
            if (hasCategory || hasSearchText || hasPeriod) {
                if (hasCategory) {
                    query += " And categoryID = ?";
                }

                if (hasPeriod) {
                    query += " And advertisementDateTime > curdate() - INTERVAL (DAYOFMONTH(curdate()) -1) DAY - INTERVAL ? MONTH";
                }

                if (hasSearchText) {
                    String passInQuery = query;
                    query = buildSearchQuery(passInQuery, searchArray);
                }

                // At this point the query has been built now it is time to enter the sanitized values.
                // This is slightly redundant but necessary to check all possibilities.
                if (hasCategory) {
                    stmt = connection.prepareStatement(query);
                    stmt.setString(1, category);

                    if (hasPeriod) {
                        stmt.setInt(2, month);

                        if (hasSearchText) {
                            stmt = addSearchQueryValues(stmt, searchArray, 3);
                        }
                    }

                    if (hasSearchText) {
                        stmt = addSearchQueryValues(stmt, searchArray, 2);
                    }

                } else if (hasPeriod) {
                    stmt = connection.prepareStatement(query);
                    stmt.setInt(1, month);

                    if (hasSearchText) {
                        stmt = addSearchQueryValues(stmt, searchArray, 2);
                    }

                } else {
                    stmt = connection.prepareStatement(query);
                    stmt = addSearchQueryValues(stmt, searchArray, 1);
                }
            }
            rs = stmt.executeQuery();
            return getActiveAds(rs);
        } catch (Exception e) {
            e.printStackTrace();
            return new Object[][]{};
        }
    }

    /**
     * Build db search query.
     *
     * @param query
     * @param searchArray
     * @return query
     */
    private String buildSearchQuery(String query, String[] searchArray) {
        for (String text : searchArray) {
            query += " And (advertisementTitle LIKE ? OR advertisementDetails Like ?)";
        }
        return query;
    }

    /**
     * Builds search query from variable data.
     *
     * @param stmt          the query template
     * @param searchArray   the array of variables; will be added to the query template
     * @param startIndex    the starting index of the searchArray
     * @return stmt         the query to be sent to the db
     */
    private PreparedStatement addSearchQueryValues(PreparedStatement stmt, String[] searchArray, int startIndex) {
        int index = startIndex;
        try {
            for (String text : searchArray) {
                stmt.setString(index, "%" + text + "%");
                index++;
                stmt.setString(index, "%" + text + "%");
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stmt;
    }

    /**
     * Gets the size of the Result set returned from a query.
     *
     * @param rs        Result set of a db query
     * @return count    the number of results returned from the query
     */
    private int getResultSetSize(ResultSet rs) {
        int count = 0;
        try {
            while (rs.next()) {
                count++;
            }
            rs.first();
        } catch (SQLException e) {

        }
        return count;
    }
}
