package com.aimee.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aimee.model.Employee;

public class EmployeeDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/webapptest?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO hcl_employees"
			+ "  (empName, empEmail, empPwd) VALUES " + " (?, ?, ?);";

	private static final String SELECT_EMPLOYEE_BY_ID = "select empID ,empName, empEmail, empPwd from hcl_employees where empID =?";
	private static final String SELECT_ALL_EMPLOYEES = "select * from hcl_employees";
	private static final String DELETE_EMPLOYEE_SQL = "delete from hcl_employees where empID = ?;";
	private static final String UPDATE_EMPLOYEE_SQL = "update hcl_employees set empName = ?, empEmail= ?, empPwd = ?  where empID = ?;";
	private static final String SELECT_EMPLOYEE_LOGIN = "select * from hcl_employees where empName = ? and empPwd = ?;";

	public EmployeeDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void insertEmployee(Employee employee) throws SQLException {
		System.out.println(INSERT_EMPLOYEE_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {
			preparedStatement.setString(1, employee.getEmpName());
			preparedStatement.setString(2, employee.getEmpEmail());
			preparedStatement.setString(3, employee.getEmpPwd());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);

		}
	}

	public Employee selectEmployee(int empID) {
		Employee employee = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);) {
			preparedStatement.setInt(1, empID);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String empName = rs.getString("empName");
				String empEmail = rs.getString("empEmail");
				String empPwd = rs.getString("empPwd");
				employee = new Employee(empID, empName, empEmail, empPwd);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return employee;
	}

	public List<Employee> selectAllEmployees() {

		List<Employee> employees = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int empID = rs.getInt("empID");
				String empName = rs.getString("empName");
				String empEmail = rs.getString("empEmail");
				String empPwd = rs.getString("empPwd");
				employees.add(new Employee(empID, empName, empEmail, empPwd));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return employees;
	}

	public boolean deleteEmployee(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateEmployee(Employee employee) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);) {
			statement.setString(1, employee.getEmpName());
			statement.setString(2, employee.getEmpEmail());
			statement.setString(3, employee.getEmpPwd());
			statement.setInt(4, employee.getEmpID());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public boolean validate(Employee employee) throws SQLException {
		boolean status = false;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_EMPLOYEE_LOGIN);) {
			statement.setString(1, employee.getEmpName());
			System.out.println(employee.getEmpName());
			statement.setString(2, employee.getEmpPwd());
			System.out.println(employee.getEmpPwd());
			ResultSet rs = statement.executeQuery();
			status = rs.next();
		}
		return status;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
