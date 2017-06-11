import java.io.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import java.net.InetAddress;

public class CookieServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static String sessionKey = "counter";

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Cookie cookie[] = request.getCookies();

        int counter = 1;
        if (session.getAttribute(sessionKey) != null) {
            counter = ((Integer)session.getAttribute(sessionKey)).intValue();
        }
        session.setAttribute(sessionKey,Integer.valueOf(counter+1));

        PrintWriter out = response.getWriter();

        response.setContentType("text/html; charset=utf-8");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<title>Cookie Sevlet GET</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Hello</h2>");
        out.println("<h2>" + request.getServerName()+ "</h2>");
        out.println("<h2>" + InetAddress.getLocalHost().getHostName()+ "</h2>");
        if (cookie != null){
            for (int i = 0 ; i < cookie.length ; i++){
                out.println("<h2>name:" + cookie[i].getName() + " value:" + cookie[i].getValue() + "</h2>");
            }
        }
        out.println("<h2>Session value</h2>");
        out.println("<h2>" + counter + "</h2>");
        out.println("<form method=\"GET\" action=\""
                    + request.getContextPath() + "/CookieServlet\">");
        out.println("<input type=\"submit\" value =\"counter\">" );
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
