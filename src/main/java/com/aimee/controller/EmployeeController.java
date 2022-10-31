package com.aimee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.aimee.DAO.EmployeeDAO;
import com.aimee.model.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDAO employeeDAO;

	public void init() {
		employeeDAO = new EmployeeDAO();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getServletPath();

		try {
			switch (action) {
			case "/login":
				empLogin(req, res);
				break;
			case "/home":
				empHomePage(req, res);
				break;
			case "/error":
				wrongLogin(req, res);
				break;
			case "/new":
				newEmpForm(req, res);
				break;
			case "/insert":
				insertNewEmp(req, res);
				break;
			case "/delete":
				deleteEmp(req, res);
				break;
			case "/update":
				updateEmp(req, res);
				break;
			case "/edit":
				editEmp(req, res);
				break;
			default:
				empLogForm(req, res);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void empLogForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
		dispatcher.forward(req, res);

	}

	private void wrongLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
		dispatcher.forward(req, res);

	}

	private void empHomePage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Employee> employees = employeeDAO.selectAllEmployees();
		req.setAttribute("employees", employees);
		RequestDispatcher dispatcher = req.getRequestDispatcher("success.jsp");
		dispatcher.forward(req, res);
	}

	private void empLogin(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, SQLException {
		String empName = req.getParameter("empName");
		String empPwd = req.getParameter("empPwd");
		Employee employee = new Employee();
		employee.setEmpName(empName);
		employee.setEmpPwd(empPwd);

		if (employeeDAO.validate(employee)) {
			res.sendRedirect("home");
		} else {
			res.sendRedirect("error");
		}

	}

	private void newEmpForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("new-emp.jsp");
		dispatcher.forward(req, res);

	}

	private void editEmp(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int empID = Integer.parseInt(req.getParameter("empID").trim());
		Employee employee = employeeDAO.selectEmployee(empID);
		RequestDispatcher dispatcher = req.getRequestDispatcher("new-emp.jsp");
		req.setAttribute("employee", employee);
		dispatcher.forward(req, res);

	}

	private void insertNewEmp(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SQLException {
		String empName = req.getParameter("empName");
		String empEmail = req.getParameter("empEmail");
		String empPwd = req.getParameter("empPwd");
		Employee employee = new Employee(empName, empEmail, empPwd);
		employeeDAO.insertEmployee(employee);
		res.sendRedirect("home");
	}

	private void deleteEmp(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
		int empID = Integer.parseInt(req.getParameter("empID").trim());
		employeeDAO.deleteEmployee(empID);
		res.sendRedirect("home");

	}

	private void updateEmp(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SQLException {
		int empID = Integer.parseInt(req.getParameter("empID"));
		String empName = req.getParameter("empName");
		String empEmail = req.getParameter("empEmail");
		String empPwd = req.getParameter("empPwd");
		Employee employee = new Employee(empID, empName, empEmail, empPwd);
		employeeDAO.updateEmployee(employee);
		res.sendRedirect("home");

	}
}
