package aggregator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Logout Controller
 *
 * @author Dedering
 */
@WebServlet(
        name = "Log Out",
        urlPatterns = { "/logout" }
)
public class Logout extends HttpServlet {

    /**
     *  Handles HTTP GET requests.
     *
     *@param  request                   the HttpServletRequest object
     *@param  response                   the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception  IOException       if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /* Get Servlet Context */
        ServletContext context = getServletContext();

        /* Get Session */
        HttpSession session = request.getSession();

        /* Get Dispatcher */
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/logout.jsp");

        /* Forward Dispatcher */
        dispatcher.forward(request, response);

    }

}
