package controllers;

import org.apache.log4j.Logger;
import persistence.Database;
import persistence.FeedDoa;
import persistence.USERDAO;

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
 * EditFeed Controller
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

        /* String Containing Incoming Feed Name */
        String newFeedName = "";

        /* String Containing Incoming Feed URL */
        String newFeedURL = "";

        /* Get UserName from Remote User */
        String userName = request.getRemoteUser();

        /* Set Default User ID */
        int userID = 0;

        /* Create Instance of UserDAO */
        USERDAO userDao = new USERDAO();

        /* Create Instance of FeedDoa */
        FeedDoa feedDao = new FeedDoa();

        /* Catch Exceptions */
        try {

            /* Get Database Connection */
            Database.getInstance().connect();

            /* Get User ID from User Dao */
            userID = userDao.getUserID(userName);
            logger.info("EditFeed.java - Connection Successful By User: " + userName);

        } catch (SQLException e) {

            /* SQLException - Print Stack Trace */
            logger.error("EditFeed.java - SQLException: Print Stack Trace");
            e.printStackTrace();

        } catch (Exception e) {

            /* Exception - Print Stack Trace */
            logger.error("EditFeed.java - Exception: Print Stack Trace");
            e.printStackTrace();
        }

        /* Get Parameter Map */
        Map<String, String[]> parameters = request.getParameterMap();

        /* Iterate Through Parameter Map */
        for(String parameter : parameters.keySet()) {

            /* Skip Parameters That Are Not Feed IDs */
            if(parameter.toLowerCase().startsWith("feeds")) {

                /* Get Parameters */
                String[] values = parameters.get(parameter);

                /* Check That Value Is Not Empty */
                if (0 < values.length) {

                    /* Catch Exceptions */
                    try {

                        /* Remove Current User's Records From user_feeds Table */
                        feedDao.deleteUserFeeds(userID);
                    } catch (SQLException e) {

                        /* Log Exception */
                        logger.error("SQLException in EditFeed.java");

                        /* Print Stack Trace */
                        e.printStackTrace();
                    }

                    /* Iterate Through Parameter Values */
                    for (int i = 0; request.getParameterValues("feeds").length > i; i++) {

                        /* Catch Exceptions */
                        try {

                            /* Update Current User's Records In user_feeds Table */
                            feedDao.updateUserFeeds(userID, Integer.parseInt(request.getParameterValues("feeds")[i]));
                        } catch (SQLException e) {

                            /* Print Stack Trace */
                            e.printStackTrace();
                        }
                    }
                }
            }

            /* Skip Parameters That Are Not New Feed Names */
            if(parameter.startsWith("addFeedName")) {

                /* Get Parameters */
                String[] addFeedNameValues = parameters.get(parameter);

                /* Check That Value Is Not Empty */
                if (0 < addFeedNameValues.length) {

                    /* Get Parameter */
                    newFeedName = addFeedNameValues[0];
                }
            }

            /* Skip Parameters That Are Not New Feed URLs */
            if(parameter.startsWith("addFeedURL")) {

                /* Get Parameters */
                String[] addFeedUrlValues = parameters.get(parameter);

                /* Check That Value Is Not Empty */
                if (0 < addFeedUrlValues.length) {

                    /* Get Parameter */
                    newFeedURL = addFeedUrlValues[0];
                }
            }
        }

        /* Check For Incoming Feed Name and URL */
        if(newFeedName != "" &&  newFeedURL != "") {

            /* Catch Exceptions */
            try {

                /* Insert feedName and feedURL to Feed Table */
                feedDao.insertNewFeed(newFeedName, newFeedURL);

                /* Log Success */
                logger.info("Feed Added By " + userName + ": " + newFeedName + " " + newFeedURL);

            } catch (SQLException e) {

                /* Print Stack Trace */
                e.printStackTrace();
            }
        }

        /* Catch Exceptions */
        try {

            /* Set Bean - Checkboxes */
            session.setAttribute("checkboxes", feedDao.returnFeedCheckboxes(userID));
        } catch (SQLException e) {

            /* Print Stack Trace */
            e.printStackTrace();
        }

        /* Get Dispatcher */
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/edit-feed.jsp");

        /* Forward Dispatcher */
        dispatcher.forward(request, response);
    }
}