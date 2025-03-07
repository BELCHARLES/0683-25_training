package com.ztasks.jdbc.task;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ztasks.jdbc.models.Employee;

public class EmployeeDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/incubationDB";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	public EmployeeDAO() {
		ensureEmployeeTableExists();
	}

	private void ensureEmployeeTableExists() {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			if (!isTableExists(connection, "employee")) {
				createEmployeeTable(connection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean isTableExists(Connection connection, String string) throws SQLException {
		DatabaseMetaData metaData = connection.getMetaData();
		try (ResultSet resultSet = metaData.getTables(null, null, string, new String[] { "TABLE" })) {
			return resultSet.next();
		}
	}

	private void createEmployeeTable(Connection connection) throws SQLException {
		String query = "CREATE TABLE employee (emp_id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(100) NOT NULL, " + "mobile VARCHAR(15) UNIQUE NOT NULL, email VARCHAR(100) UNIQUE NOT NULL, " + "department VARCHAR(50) NOT NULL)";
		try(Statement stmt = connection.createStatement()){
			stmt.executeUpdate(query);
		}
	}
	
	 public boolean insertEmployee(Employee emp) {
	        String sql = "INSERT INTO Employee (name, mobile, email, department) VALUES (?, ?, ?, ?)";

	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            
	            pstmt.setString(1, emp.getName());
	            pstmt.setString(2, emp.getMobile());
	            pstmt.setString(3, emp.getEmail());
	            pstmt.setString(4, emp.getDepartment());

	            return pstmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	
}
