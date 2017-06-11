import java.io.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;

public class NormalServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        response.setContentType("text/html; charset=utf-8");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<title>Cookie Sevlet GET</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + request.getServerName()+ "</h2>");
        out.println("<form method=\"GET\" action=\""
                    + request.getContextPath() + "/NormalServlet\">");
        out.println("<input type=\"submit\" value =\"normal\">" );
        out.println("</form>");
        out.println("<form method=\"GET\" action=\""
                    + request.getContextPath() + "/CookieServlet\">");
        out.println("<input type=\"submit\" value =\"counter\">" );
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
