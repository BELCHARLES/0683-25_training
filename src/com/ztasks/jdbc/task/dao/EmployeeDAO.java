package com.ztasks.jdbc.task.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.exception.ValidationException;
import com.ztasks.jdbc.models.Employee;

public class EmployeeDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/incubationDB";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	public EmployeeDAO() throws SQLException {
		createEmployeeTable();
	}

	/*
	 * private void ensureEmployeeTableExists() throws SQLException { try
	 * (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
	 * if (!isTableExists(connection, "employee")) {
	 * createEmployeeTable(connection); } } }
	 * 
	 * private boolean isTableExists(Connection connection, String tableName) throws
	 * SQLException { DatabaseMetaData metaData = connection.getMetaData(); try
	 * (ResultSet resultSet = metaData.getTables(null, null, tableName, new String[]
	 * { "TABLE" })) { return resultSet.next(); } }
	 */

	private void createEmployeeTable() throws SQLException {
		String query = "CREATE TABLE IF NOT EXISTS employee (emp_id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(100) NOT NULL, mobile VARCHAR(15) UNIQUE NOT NULL, email VARCHAR(100) UNIQUE NOT NULL, department VARCHAR(50) NOT NULL)";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = connection.createStatement()) {
			stmt.executeUpdate(query);
		}
	}

	public void insertEmployee(List<Employee> employees) throws SQLException {
		String sql = "INSERT INTO employee (name, mobile, email, department) VALUES (?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			 for (Employee emp : employees) {
		            pstmt.setString(1, emp.getName());
		            pstmt.setString(2, emp.getMobile());
		            pstmt.setString(3, emp.getEmail());
		            pstmt.setString(4, emp.getDepartment());

		            pstmt.addBatch(); 
		        }

		        pstmt.executeBatch();
		}
	}

	public List<Employee> retrieveDetailsByName(String name) throws SQLException {
		String sql = "SELECT * FROM employee WHERE name = ?";
		List<Employee> employeeList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, name);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Employee employee = mapResultSetToEmployee(rs);
					employeeList.add(employee);
				}
			}
		}

		return employeeList;
	}

	public List<Employee> getEmployees() throws SQLException {
		return getEmployees(-1, false, null, false);
	}

	public List<Employee> getEmployees(int limit) throws SQLException {
		return getEmployees(limit, false, null, false);
	}

	public List<Employee> getEmployees(int limit, boolean withSorting, String columnToSort, boolean isAscending)
			throws SQLException {
		List<Employee> employeeList = new ArrayList<>();
		StringBuilder query = new StringBuilder("select * from employee");
		if (withSorting) {
			query.append(" Order by ").append(columnToSort).append(isAscending ? " ASC " : " DESC ");
		}

		if (limit > 0) {
			query.append(" limit ?");
		}

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(query.toString())) {

			if (limit > 0) {
				pstmt.setInt(1, limit);
			}

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Employee employee = mapResultSetToEmployee(rs);
					employeeList.add(employee);
				}
			}
		}

		return employeeList;
	}

	public boolean updateEmployee(int id,String columnName, String newValue) throws SQLException {
		StringBuilder query = new StringBuilder("update employee set ");
		query.append(columnName).append(" = ? ").append("where emp_id = ?");

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
			pstmt.setString(1,newValue);
			pstmt.setInt(2, id);
			return pstmt.executeUpdate() > 0;
		}
	}

	public Employee retrieveDetailsById(int id) throws SQLException, ValidationException {
		String query = "SELECT * FROM employee WHERE emp_id = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setInt(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return mapResultSetToEmployee(rs);
				}

			}
		}
		throw new ValidationException("Employee with "+id+" not found");

	}
	
	public boolean deleteEmployeeById(int id) throws SQLException {
		String query = "delete from employee where emp_id = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setInt(1, id);
			return pstmt.executeUpdate()>0;
		}
	}

	private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getInt("emp_id"));
		employee.setName(rs.getString("name"));
		employee.setMobile(rs.getString("mobile"));
		employee.setEmail(rs.getString("email"));
		employee.setDepartment(rs.getString("department"));
		return employee;
	}



}
