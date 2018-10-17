import java.io.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class CookieServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  private static String sessionKey = "counter";

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession(true);
    Cookie cookie[] = request.getCookies();

    int counter = 1;
    if (session.getAttribute(sessionKey) != null) {
      counter = ((Integer) session.getAttribute(sessionKey)).intValue();
    }

    PrintWriter out = response.getWriter();

    response.setContentType("text/html; charset=utf-8");

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"utf-8\">");
    out.println("<title>Cookie Sevlet GET</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h2>[AP Server]: " + request.getServerName() + "</h2>");
    if (cookie != null) {
      for (int i = 0; i < cookie.length; i++) {
        out.println(
            "<h2>[Cookie Name]:"
                + cookie[i].getName()
                + " [Cookie Value]:"
                + cookie[i].getValue()
                + "</h2>");
      }
    }
    out.println("<h2>[Counter]: " + counter + "</h2>");
    out.println("<form method=\"GET\" action=\"" + request.getContextPath() + "/NormalServlet\">");
    out.println("<input type=\"submit\" value =\"normal\">");
    out.println("</form>");
    out.println("<form method=\"GET\" action=\"" + request.getContextPath() + "/CookieServlet\">");
    out.println("<input type=\"submit\" value =\"view counter\">");
    out.println("</form>");
    out.println("<form method=\"POST\" action=\"" + request.getContextPath() + "/CookieServlet\">");
    out.println("<input type=\"submit\" value =\"count up\">");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession(true);
    Cookie cookie[] = request.getCookies();

    int counter = 1;
    if (session.getAttribute(sessionKey) != null) {
      counter = ((Integer) session.getAttribute(sessionKey)).intValue();
      counter++;
    }
    session.setAttribute(sessionKey, Integer.valueOf(counter));

    PrintWriter out = response.getWriter();

    response.setContentType("text/html; charset=utf-8");

    response.setContentType("text/html; charset=utf-8");

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"utf-8\">");
    out.println("<title>Cookie Sevlet GET</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h2>[AP Server]: " + request.getServerName() + "</h2>");
    if (cookie != null) {
      for (int i = 0; i < cookie.length; i++) {
        out.println(
            "<h2>[Cookie Name]:"
                + cookie[i].getName()
                + " [Cookie Value]:"
                + cookie[i].getValue()
                + "</h2>");
      }
    }
    out.println("<h2>[Counter]: " + counter + "</h2>");
    out.println("<form method=\"GET\" action=\"" + request.getContextPath() + "/NormalServlet\">");
    out.println("<input type=\"submit\" value =\"normal\">");
    out.println("</form>");
    out.println("<form method=\"GET\" action=\"" + request.getContextPath() + "/CookieServlet\">");
    out.println("<input type=\"submit\" value =\"view counter\">");
    out.println("</form>");
    out.println("<form method=\"POST\" action=\"" + request.getContextPath() + "/CookieServlet\">");
    out.println("<input type=\"submit\" value =\"count up\">");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }

  private void hello() {
    System.out.println("hello\n");
  }
}
