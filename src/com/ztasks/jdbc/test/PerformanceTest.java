package com.ztasks.jdbc.test;

import java.sql.*;

public class PerformanceTest{
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        int totalRecords = 1000; // Number of records to insert

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.createStatement().execute("TRUNCATE TABLE employee");

            long statementTime = insertWithStatement(conn, totalRecords);
            System.out.println("Time taken using Statement: " + statementTime + " ms");

            conn.createStatement().execute("TRUNCATE TABLE employee");

            long preparedStatementTime = insertWithPreparedStatement(conn, totalRecords);
            System.out.println("Time taken using PreparedStatement: " + preparedStatementTime + " ms");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Using Statement (Less Efficient)
    public static long insertWithStatement(Connection conn, int totalRecords) throws SQLException {
        long startTime = System.currentTimeMillis();
        Statement stmt = conn.createStatement();

        for (int i = 1; i <= totalRecords; i++) {
            String sql = "INSERT INTO employee (name, age, department) VALUES "
                    + "('Employee" + i + "', " + (20 + (i % 10)) + ", 'Dept" + (i % 5) + "')";
            stmt.executeUpdate(sql); // Each query is compiled separately
        }

        return System.currentTimeMillis() - startTime;
    }

    // Using PreparedStatement (More Efficient for Repeated Execution)
    public static long insertWithPreparedStatement(Connection conn, int totalRecords) throws SQLException {
        long startTime = System.currentTimeMillis();
        String sql = "INSERT INTO employee (name, age, department) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 1; i <= totalRecords; i++) {
                pstmt.setString(1, "Employee" + i);
                pstmt.setInt(2, 20 + (i % 10));
                pstmt.setString(3, "Dept" + (i % 5));
                pstmt.addBatch();

                if (i % 500 == 0) { // Execute in batches for better performance
                    pstmt.executeBatch();
                }
            }

            pstmt.executeBatch(); // Final execution for remaining records
        }

        return System.currentTimeMillis() - startTime;
    }
}
