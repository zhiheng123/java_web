package register;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class Register extends HttpServlet {
	private String name;
	private String pass;
	private String confirm;

	public synchronized void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.name = request.getParameter("account");
		this.pass = request.getParameter("password");
		this.confirm = request.getParameter("confirm");
		PrintWriter out = response.getWriter();
		// ����������
		String driverName = "com.mysql.jdbc.Driver";
		// ���ݿ��û���
		String userName = "root";
		// ����
		String userPasswd = "123456";
		// ���ݿ���
		String dbName = "database";
		// ����
		String tableName = "users";
		// �����ַ���
		String url = "jdbc:mysql://localhost/" + dbName + "?user=" + userName + "&password=" + userPasswd;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			Connection connection = DriverManager.getConnection(url);
			Statement statement = connection.createStatement();

			String sql = "SELECT * FROM " + tableName;
			ResultSet rs = statement.executeQuery(sql);

			while (true) {
				if (rs.next()) {
					if (this.name.equals(rs.getString(1))) {
						out.print("<h1>" + "User Name is in Database!");
						break;
					}
				} else {
					sql = "INSERT INTO " + tableName + " VALUES('" + this.name + "','" + this.pass + "')";
					statement.execute(sql);
					out.print("<h1>" + "Register Successful!");
					break;
				}
			}
			return;
		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {
		}

	}

	public synchronized void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}