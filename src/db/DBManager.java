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
 *
 * @author jwolf, lsimmons
 */
public class DBManager {

    Connection connection;

    public void connect(String userName, String password, String serverName,
            String portNumber, String dbName)
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

    public boolean checkUser(String employeeID) {
        String query = "select * FROM Users WHERE userID=?";
        return checkEmployee(query, employeeID);
    }

    public boolean checkModerator(String employeeID) {
        String query = "select * FROM Moderators WHERE userID=?";
        return checkEmployee(query, employeeID);
    }

    private boolean checkEmployee(String query, String employeeID) {
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, employeeID); //binding the parameter with the given string
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

    public Advertisement getAdByID(int id){
        PreparedStatement stmt = null;
        Advertisement result = new Advertisement();
        String query = "Select advertisementID, advertisementTitle, advertisementDetails, price, categoryID " +
                "From Advertisements Where advertisementID = ?";
        try{
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            result.setID(rs.getInt("advertisementID"))
                    .setTitle(rs.getString("advertisementTitle"))
                    .setDetails(rs.getString("advertisementDetails"))
                    .setPrice(rs.getFloat("price"))
                    .setCategoryID(rs.getString("categoryID"));
                    
//            result = new Object[5];            
//            while (rs.next()){
//                result[0] = rs.getInt("advertisementID");
//                result[1] = rs.getString("advertisementTitle");
//                result[2] = rs.getString("advertisementDetails");
//                result[3] = rs.getDouble("price");
//                result[4] = rs.getString("categoryID");
//            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;

    }
    public Object[][] getAllActiveAds() {
        PreparedStatement stmt = null;
        Object[][] results = new Object[][]{};

        String query = "SELECT A.advertisementTitle, A.advertisementDetails, "
                + "A.price, DATE(A.advertisementDateTime) advertisementDate "
                + "FROM Advertisements A "
                + "WHERE statusID = 'AC'";

        try {
            stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            results = getActiveAds(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    private Object[][] getActiveAds(ResultSet rs) throws SQLException {
        int count = getResultSetSize(rs);
        Object[][] result = new Object[count][4];
        int index = 0;
        do {
            String title = rs.getString("advertisementTitle");
            String details = rs.getString("advertisementDetails");
            float price = rs.getFloat("price");
            String date = rs.getString("advertisementDate");

            Advertisement advertisement = new Advertisement(title, details, price, date);
            result[index++] = advertisement.activeAdsToArray();
        } while (rs.next());
        return result;
    }

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

    private Object[][] getUserAds(int count, ResultSet rs) throws SQLException {
        Object[][] result = new Object[count][6];
        int index = 0;
        do {
            int id = rs.getInt("advertisementID");
            String title = rs.getString("advertisementTitle");
            String details = rs.getString("advertisementDetails");
            float price = rs.getFloat("price");
            String status = rs.getString("statusID");
            String date = rs.getString("advertisementDate");

            Advertisement advertisement = new Advertisement(id, title, details, price, status, date);
            result[index++] = advertisement.userAdsToArray();
        } while (rs.next());
        return result;
    }

    public boolean addAdvertisement(String title, String details, String price, String category,
            String userID) {
        PreparedStatement stmt = null;

        String query = "INSERT INTO Advertisements (advertisementTitle, advertisementDetails, "
                + "advertisementDateTime, price, categoryID, userID, statusID) "
                + "VALUES (?,?,CURRENT_DATE(),?,?,?,?)";

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, details);
            stmt.setString(3, price);
            stmt.setString(4, category);
            stmt.setString(5, userID);
            stmt.setString(6, "PN");
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
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

public boolean moderatorUpdateAdvertisement(String ID, String category, String status) {
        PreparedStatement stmt = null;

        String query = "UPDATE Advertisements "
                + "SET categoryID= ?, statusID= ?) "
                + "WHERE advertisementID= ? "
                + "VALUES (?,?,?)";

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, category);
            stmt.setString(2, "PN");
            stmt.setString(3, ID);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        
    public Object[][] getAllUnclaimedAds() {
        PreparedStatement stmt = null;
        Object[][] results = new Object[][]{};

        String query = "SELECT A.advertisementID, A.advertisementTitle, A.advertisementDetails, "
                + "A.price, A.categoryID, A.userID, DATE(A.advertisementDateTime) advertisementDate "
                + "FROM Advertisements A "
                + "WHERE moderatorID IS NULL";

        try {
            stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            results = getUnclaimedAds(rs);
        } catch (Exception e) {
            e.printStackTrace();
            return results;
        }
        return results;
    }

    private Object[][] getUnclaimedAds(ResultSet rs) throws SQLException {
        int count = getResultSetSize(rs);
        Object[][] result = new Object[count][7];
        int index = 0;
        do {
            int id = rs.getInt("advertisementID");
            String title = rs.getString("advertisementTitle");
            String details = rs.getString("advertisementDetails");
            float price = rs.getFloat("price");
            String category = rs.getString("categoryID");
            String user = rs.getString("userID");
            String date = rs.getString("advertisementDate");

            Advertisement advertisement = new Advertisement(id, title, details, price, 
                    category, user, date);
            result[index++] = advertisement.unclaimedAdsToArray();
        } while (rs.next());
        return result;
    }

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
            results = getUserAds(count, rs);
        } catch (Exception e) {
            e.printStackTrace();
            return results;
        }
        return results;
    }

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

    public Object[][] searchActiveAds(String category, String period, String searchText) {
        PreparedStatement stmt = null;
        ResultSet rs;
        int month = Utilities.getMonth(period);
        boolean hasCategory = !category.equals("ALL");
        boolean hasPeriod = month != -1;
        boolean hasSearchText = !StringUtils.isNullOrEmpty(searchText);
        String[] searchArray = searchText.split(" ");
        String query = "Select advertisementTitle, advertisementDetails, price, "
                + "Date(advertisementDateTime) as advertisementDate "
                + "From Advertisements "
                + "Where statusID = 'AC'";
        try {
            stmt = connection.prepareStatement(query);
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

    private String buildSearchQuery(String query, String[] searchArray) {
        for (String text : searchArray) {
            query += " And (advertisementTitle LIKE ? OR advertisementDetails Like ?)";
        }
        return query;
    }

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
