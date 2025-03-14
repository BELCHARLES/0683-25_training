package com.ztasks.jdbc.test;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class PreparedStatementPoolTest {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final int CACHE_CAPACITY = 2;  

    private Map<String, PreparedStatement> statementPool = new LinkedHashMap<String, PreparedStatement>(CACHE_CAPACITY, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, PreparedStatement> eldest) {
            if (size() > CACHE_CAPACITY) {
                try {
                    System.out.println("-- Removing LRU PreparedStatement: " + eldest.getKey());
                    eldest.getValue().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return true;
            }
            return false;
        }
    };

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        if (statementPool.containsKey(sql)) {
            System.out.println("|| Reusing existing PreparedStatement for: " + sql);
            return statementPool.get(sql);  // Reusing cached statement (but may fail if connection closed)
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("++ Creating new PreparedStatement for: " + sql);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            statementPool.put(sql, pstmt);  // Cached but tied to a closed connection
            return pstmt;  // 🚨 Problem: This statement is linked to a closed connection
        }
    }

    public void insertStudent(String name, int age, String grade) throws SQLException {
        String insertStudentSQL = "INSERT INTO students (name, age, grade) VALUES (?, ?, ?)";
        PreparedStatement pstmt = getPreparedStatement(insertStudentSQL);

        pstmt.setString(1, name);
        pstmt.setInt(2, age);
        pstmt.setString(3, grade);

        pstmt.executeUpdate();
        System.out.println("Inserted Student: " + name);
    }

    public void selectFromStudents(int id) throws SQLException {
        String selectSQL = "SELECT * FROM students WHERE id = ?";
        PreparedStatement pstmt = getPreparedStatement(selectSQL);

        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();  // 🚨 Expected ERROR here

        if (rs.next()) {
            System.out.println("== Student ID: " + rs.getInt("id"));
            System.out.println("== Student Name: " + rs.getString("name"));
        } else {
            System.out.println("No student found with ID: " + id);
        }
    }

    public static void main(String[] args) {
        try {
            PreparedStatementPool pool = new PreparedStatementPool();

            pool.insertStudent("Alice Johnson", 20, "A");
            pool.insertStudent("Bob Williams", 22, "B");

            pool.selectFromStudents(1);  // 🚨 Error will occur here
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
