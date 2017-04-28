/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 *
 * @author jwolf, lsimmons
 */
public class DBManager {

    Connection connection;

    public class Record {

        public String ID;
        public String Name;

        public Record(String ID, String Name) {
            this.ID = ID;
            this.Name = Name;
        }

        public String toString() {
            return Name;
        }
    }

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
    
    public Object[][] getAdsByUser(String userID) {
        String query = "SELECT advertisementID, advertisementTitle, advertisementDetails, price, Statuses.statusName, advertisementDateTime "
                + "FROM Advertisements "
                + "INNER JOIN Statuses ON Statuses.statusID = Advertisements.statusID "
                + "WHERE userID = ?";
        return getAdvertisement(query, userID);
    }

//    public Object[][] getAdsByModerator(String moderatorID) {
//        String query = "Select advertisementID, advertisementTitle, advertisementDetails, price, Statuses.statusName, advertisementDateTime "
//                + "FROM Advertisements "
//                + "INNER JOIN "
//        return getAdvertisement(query, moderatorID);
//    }
//    
//    public Object[][] getAdsByStatus(String status) {
//        String query = "SELECT advertisementID, advertisementTitle, advertisementDetails, price, Statuses.statusName, advertisementDateTime "
//                + "FROM Advertisements "
//                + "INNER JOIN Statuses ON Statuses.statusID = Advertisements.statusID "
//                + "WHERE statusID = ?";
//        return getAdvertisement(query, category, period, status);
//    }

    private Object[][] getAdvertisement(String query, String userID) {
        PreparedStatement stmt = null;
        Object[][] results = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, userID);
            ResultSet rs = stmt.executeQuery();
            int index = 0;
            results = new Object[getResultSetSize(rs)][6];
            while (rs.next()) {
                results[index][0] = rs.getInt("advertisementID");
                results[index][1] = rs.getString("advertisementTitle");
                results[index][2] = rs.getString("advertisementDetails");
                results[index][3] = rs.getDouble("price");
                results[index][4] = rs.getString("statusName");
                results[index][5] = rs.getDate("advertisementDateTime");
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
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
