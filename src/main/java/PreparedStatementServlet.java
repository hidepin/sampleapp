import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class PreparedStatementServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>P</title>");
		out.println("</head>");
		out.println("<body>");

		Connection conn = null;

		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/dbDS");
			conn = ds.getConnection();

			String sql = "SELECT i_id, i_im_id, i_name from item where i_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				int i_id = rs.getInt("i_id");
				int i_im_id = rs.getInt("i_im_id");
				String i_name= rs.getString("i_name");
				out.println("<p>");
				out.println("i_id:" + i_id + ", i_im_id:" + i_im_id + ", i_name:" + i_name);
				out.println("</p>");
			}

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			out.println("SQLException:" + e.getMessage());
		} catch (Exception e) {
			out.println("Exception:" + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				out.println("SQLException:" + e.getMessage());
			}
		}

		out.println("</body>");
		out.println("</html>");
	}
}
