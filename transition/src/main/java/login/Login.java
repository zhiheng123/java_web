package login;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

//import com.mysql.jdbc.*;
public class Login extends HttpServlet {
	private String name;
	private String pass;

	public synchronized void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.name = request.getParameter("account");
		this.pass = request.getParameter("password");
		PrintWriter out = response.getWriter();
		// 驱动程序名
		String driverName = "com.mysql.jdbc.Driver";
		// 数据库用户名
		String userName = "root";
		// 密码
		String userPasswd = "123";
		// 数据库名
		String dbName = "database";
		// 表名
		String tableName = "users";
		// 联结字符串
		String url = "jdbc:mysql://localhost/" + dbName + "?user=" + userName + "&password=" + userPasswd;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			Connection connection = DriverManager.getConnection(url);
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM " + tableName;
			ResultSet rs = statement.executeQuery(sql);
			while (true) {
				if (rs.next()) {
					if ((this.name.equals(rs.getString(1))) && (this.pass.equals(rs.getString(2)))) {

						out.print("<h1>" + "Login Success!");

						break;
					}
				} else {
					out.print("<h1>" + "Account or Password Is Invalid!");
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