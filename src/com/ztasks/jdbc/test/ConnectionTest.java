package com.ztasks.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//public class ConnectionTest {
//	private static final String URL = "jdbc:mysql://localhost:3306/testdb";
//	private static final String USER = "root";
//	private static final String PASSWORD = "root";
//
//	public static void main(String []args) throws SQLException {
//		try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)){
//			String query = "select * from students where id = ?";
//			PreparedStatement pstmt = connection.prepareStatement(query);
//			pstmt.setInt(1, 1);
//			ResultSet rs = pstmt.executeQuery();
//			if(rs.next()) {
//				System.out.println("id: "+rs.getInt("id")+" name : "+rs.getString("name"));
//			}
//		}
//	}
//}



import java.sql.*;

public class ConnectionTest {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        PreparedStatement pstmt = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("✅ Connection established successfully.");

            String selectSQL = "SELECT * FROM students WHERE id = ?";
            pstmt = connection.prepareStatement(selectSQL);

            pstmt.setInt(1, 1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Attempting to use the closed PreparedStatement...");
            ResultSet rs = pstmt.executeQuery();  

            if (rs.next()) {
                System.out.println("== Student ID: " + rs.getInt("id"));
                System.out.println("== Student Name: " + rs.getString("name"));
            } else {
                System.out.println("No student found.");
            }
        } catch (SQLException e) {
            System.err.println("Exception Occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
