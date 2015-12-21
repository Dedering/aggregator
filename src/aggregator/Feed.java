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
import java.util.List;

/**
 * Feed Servlet
 *
 * @author Dedering
 */

@WebServlet(
        name = "Feed",
        urlPatterns = { "/feed" }
)
public class Feed extends HttpServlet {

    /* Get Logger */
    private Logger logger = Logger.getLogger(this.getClass());

    /* Set Default UserID */
    private int userID = 0;

    /* Set Default UserName*/
    private String userName = "default";

    /**
     * Handles HTTP GET requests.
     *
     * @param  request                   the HttpServletRequest object
     * @param  response                   the HttpServletResponse object
     * @exception  ServletException  if there is a Servlet failure
     * @exception  IOException       if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /* Get Servlet Context */
        ServletContext context = getServletContext();

        /* Get Session */
        HttpSession session = request.getSession();

        /* Get Username */
        userName = request.getRemoteUser();

        /* Connect to FeedDao */
        FeedDoa feedDao = new FeedDoa();

        /* Connect to UserDao */
        USERDAO dao = new USERDAO();

        /* Initialize Feed Builder */
        FeedBuilder feed = null;

        /* Arraylist of Incoming <link> Element Values */
        List<Parse> feeds = null;

        /* Catch Exceptions */
        try {
            /* Get Database Connection */
            Database.getInstance().connect();

            /* Get Feed  */
            feed = new FeedBuilder(dao.getUserID(userName));

            /* Set Bean - feedLinks */
            session.setAttribute("feedLinks", feed.feedLinks());

            /* Set Bean - feed */
            session.setAttribute("feed", feed.parseFeed());

        } catch (Exception e) {

            /* Print Stack Trace */
            e.printStackTrace();

            /* Log Exception */
            logger.error("Exception in Feed.java doGet");
        }

        /* Get Dispatcher */
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/feed.jsp");

        /* Forward Dispatcher */
        dispatcher.forward(request, response);
    }
}