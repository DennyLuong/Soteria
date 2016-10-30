package com.example.looka.soteria;

import java.sql.*;

class UpdateMySqlDatabase {
    public UpdateMySqlDatabase() {
        String url = "jdbc:mysql://ec2-54-172-30-46.compute-1.amazonaws.com:3306/";
        String userName = "root";
        String password = "5aRQKp2O";
        String dbName = "location";
        String driver = "com.mysql.jdbc.Driver";
        try {
            Connection connection = DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Connection successful.");
            Statement stmt = null;
            String query = "select * from gps";
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                float latitude = rs.getFloat("latitude");
                float longitude = rs.getFloat("longitude");
                String timestamp = rs.getString("timestamp");
                System.out.println("Latitude: " + latitude);
                System.out.println("Longitude: " + longitude);
                System.out.println("Timestamp: " + timestamp);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}