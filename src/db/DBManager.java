/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

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

//    public Object[][] getAllActiveAds() {
//        PreparedStatement stmt = null;
//        Object[][] results = new Object[][]{};
//        
//        String query = "SELECT A.advertisementDateTime, A.advertisementTitle, "
//                + "A.advertisementDetails, A.price"
//                + "FROM Advertisements A "
//                + "INNER JOIN Statuses S ON S.statusID=A.statusID "
//                + "WHERE statusName = 'ACTIVE'";
//        
//        try {
//            stmt = connection.prepareStatement(query);
//            ResultSet rs = stmt.executeQuery();
//            int count = getResultSetSize(rs);
//            int index = 0;
//            Object[][] result = new Object[count][4];
//            while (rs.next()) {
//                result[index][0] = rs.getString("advertisementTitle");
//                result[index][1] = rs.getString("advertisementDetails");
//                result[index][2] = rs.getDouble("price");
//                result[index][3] = rs.getDate("advertisementDateTime");
//                index++;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return results;
//    }
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
            return results;
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
            String price = rs.getString("price");
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
            String id = rs.getString("advertisementID");
            String title = rs.getString("advertisementTitle");
            String details = rs.getString("advertisementDetails");
            String price = rs.getString("price");
            String status = rs.getString("statusID");
            String date = rs.getString("advertisementDate");

            Advertisement advertisement = new Advertisement(id, title, details, price, status, date);
            result[index++] = advertisement.userAdsToArray();
        } while (rs.next());
        return result;
    }

//public Object[][] getAdsByStatus(String status) {
//        String query = "SELECT advertisementID, advertisementDateTime, "
//                + "advertisementTitle, advertisementDetails, price, Statuses.statusName, "
//                + "FROM Advertisements "
//                + "INNER JOIN Statuses ON Statuses.statusID=Advertisements.statusID "
//                + "WHERE statusID = ?";
//        return getAdvertisementByStatus(query, status);
//    }
//    public Object[][] getAdsByUser(String userID) {
//        String query = "SELECT advertisementDateTime, advertisementTitle, advertisementDetails, price "
//                + "FROM Advertisements "
//                + "INNER JOIN Statuses ON Statuses.statusID = Advertisements.statusID "
//                + "WHERE userID = ?";
//        return getAdvertisementByUser(query, userID);
//    }
//    public Object[][] getAdsByModerator(String moderatorID) {
//        String query = "Select advertisementID, advertisementTitle, advertisementDetails, price, Statuses.statusName, advertisementDateTime "
//                + "FROM Advertisements "
//                + "INNER JOIN "
//      return getAdvertisementByUser(query, userID);
//    }
//
//    private Object[][] getAdvertisementByUser(String query, String userID) {
//        PreparedStatement stmt = null;
//        Object[][] results = null;
//        try {
//            stmt = connection.prepareStatement(query);
//            stmt.setString(1, userID);
//            ResultSet rs = stmt.executeQuery();
//            int index = 0;
//            results = new Object[getResultSetSize(rs)][6];
//            while (rs.next()) {
//                results[index][0] = rs.getInt("advertisementID");
//                results[index][1] = rs.getDate("advertisementDateTime");
//                results[index][2] = rs.getString("advertisementTitle");
//                results[index][3] = rs.getString("advertisementDetails");
//                results[index][4] = rs.getDouble("price");
//                results[index][5] = rs.getString("statusName");
//                index++;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return results;
//    }
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
