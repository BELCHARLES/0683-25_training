package com.ztasks.jdbc.task.dao;


import java.sql.*;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ztasks.jdbc.models.Nominee;

public class NomineeDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/incubationDB";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public NomineeDAO() throws SQLException {
        ensureNomineeTableExists();
    }

    private void ensureNomineeTableExists() throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (!isTableExists(connection, "nominee")) {
                createNomineeTable(connection);
            }
        }
    }

    private boolean isTableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet resultSet = metaData.getTables(null, null, tableName, new String[]{"TABLE"})) {
            return resultSet.next();
        }
    }

    private void createNomineeTable(Connection connection) throws SQLException {
    	String query = "CREATE TABLE nominee (nominee_id INT PRIMARY KEY AUTO_INCREMENT, emp_id INT NOT NULL, name VARCHAR(100) NOT NULL, age INT NOT NULL, relationship VARCHAR(50) NOT NULL, CONSTRAINT fk_employee FOREIGN KEY (emp_id) REFERENCES employee(emp_id) ON DELETE CASCADE ON UPDATE CASCADE)";

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        }
    }

    public void insertNominee(Nominee nominee) throws SQLException {
        String query = "INSERT INTO nominee (emp_id, name, age, relationship) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, nominee.getEmpId());
            pstmt.setString(2, nominee.getName());
            pstmt.setInt(3, nominee.getAge());
            pstmt.setString(4, nominee.getRelationship());

            pstmt.executeUpdate();
        }
    }

	public JSONArray getNomineeByEmpId(int id) throws SQLException {
		String query = "Select e.emp_id,e.name as employee_name, n.nominee_id,n.name as nominee_name,n.age,n.relationship from employee e join nominee n on e.emp_id = n.emp_id where e.emp_id = ?";
		JSONArray array = new JSONArray();
		 try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement pstmt = conn.prepareStatement(query)) {

	            pstmt.setInt(1, id);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
					JSONObject obj =  mapResultSetToJSON(rs);
					array.put(obj);
				}
	            return array;
	        }
	}

	private JSONObject mapResultSetToJSON(ResultSet rs) throws SQLException {
	    JSONObject obj = new JSONObject();

	    obj.put("employeeId", rs.getInt("emp_id"));
	    obj.put("employeeName", rs.getString("employee_name"));
	    obj.put("nomineeId", rs.getInt("nominee_id"));
	    obj.put("nomineeName", rs.getString("nominee_name"));
	    obj.put("nomineeAge", rs.getInt("age"));
	    obj.put("relationship", rs.getString("relationship"));

	    return obj;
	}

	public JSONArray getEmployeesNomineeDetails(int limit, boolean isAscending) throws SQLException {
	    String sortOrder = isAscending ? "ASC" : "DESC";
//	    String query = "SELECT e.emp_id, e.name AS employee_name, n.nominee_id, n.name AS nominee_name, n.age, n.relationship FROM employee e left JOIN nominee n ON e.emp_id = n.emp_id ORDER BY e.name " + sortOrder + ",n.name "+sortOrder+" LIMIT ?";
	    String query = "SELECT e.emp_id, e.name AS employee_name, COALESCE(n.nominee_id, 0) AS nominee_id, COALESCE(n.name, '') AS nominee_name, COALESCE(n.age, 0) AS age, COALESCE(n.relationship, '') AS relationship FROM employee e LEFT JOIN nominee n ON e.emp_id = n.emp_id ORDER BY e.name " + sortOrder + ", n.name " + sortOrder + " LIMIT ?";


	    JSONArray array = new JSONArray();
	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement pstmt = conn.prepareStatement(query)) {

	        pstmt.setInt(1, limit);
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	        	JSONObject obj =  mapResultSetToJSON(rs);
				array.put(obj);
	        }
	    }
	    return array;
	}


    
    
}
