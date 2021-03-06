import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

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
		out.println("<title>PreparedStatement</title>");
		out.println("</head>");
		out.println("<body>");

		Connection conn = null;

		try {
			InitialContext cxt = new InitialContext();
			DataSource ds = (DataSource)cxt.lookup("java:/comp/env/jdbc/dbDS");
			conn = ds.getConnection();

			int argnum = new Random().nextInt(10000) + 1;
			String parg = "?";
			for (int i = 0; i < argnum - 1; i++) {
				parg = parg + ", ?";
			}
			String sql = "SELECT i_id, i_im_id, i_name FROM item WHERE i_id in (" + parg + ")";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (int i = 1; i <= argnum; i++) {
				pstmt.setInt(i, argnum);
			}
			ResultSet rs = pstmt.executeQuery();

			String i_name = null;
			while (rs.next()) {
				int i_id = rs.getInt("i_id");
				int i_im_id = rs.getInt("i_im_id");
				i_name= rs.getString("i_name");
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
