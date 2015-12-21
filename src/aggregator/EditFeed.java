package aggregator;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * EditFeed Servlet
 *
 * @author Dedering
 */

@WebServlet(
        name = "Edit Feed",
        urlPatterns = { "/edit-feed" }
)
public class EditFeed extends HttpServlet {

    /* Get Logger */
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     *  Handles HTTP GET requests.
     *
     *@param  request                   the HttpServletRequest object
     *@param  response                   the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception  IOException       if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws  ServletException, IOException {

        /* Get Servlet Context */
        ServletContext context = getServletContext();

        /* Get Session */
        HttpSession session = request.getSession();

        /* Get UserName from Remote User */
        String userName = request.getRemoteUser();

        /* Create Instance of Account */
        Account account = new Account();

        /* Create Instance of UserDAO */
        USERDAO userDao = new USERDAO();

        /* Create Instance of FeedDoa */
        FeedDoa feedDao = new FeedDoa();

        /* Get Parameters from request */
        Map<String, String[]> parameters = request.getParameterMap();

        /* Get Feed Parameter from Map */
        String[] paramValues = request.getParameterValues("feeds");

        /* Set Default User ID */
        int userID = 0;

        /* Catch Exceptions */
        try {

            /* Get Database Connection */
            Database.getInstance().connect();

            /* Get UserID by UserName */
            userID = userDao.getUserID(userName);

            /* Pass params to Account Class */
            account.updateActiveFeeds(parameters, userName, paramValues);

             /* Set Bean - Checkboxes */
            session.setAttribute("checkboxes", feedDao.returnFeedCheckboxes(userID));

            /* Log successful form submission */
            logger.info("EditFeed.java - Successful Form Submission By User: " + userName);

        } catch (SQLException e) {

            /* Log Exception */
            logger.error("SQL Exception");

            /* SQLException - Print Stack Trace */
            e.printStackTrace();

        } catch (Exception e) {

            /* Log Exception */
            logger.error("Exception");

            /* Exception - Print Stack Trace */
            e.printStackTrace();
        }


        /* Get Dispatcher */
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/edit-feed.jsp");

        /* Forward Dispatcher */
        dispatcher.forward(request, response);
    }
}