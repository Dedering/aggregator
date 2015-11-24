package aggregator;

/**
 * Created by Private on 11/22/15.
 */

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(
        name = "Edit Feed",
        urlPatterns = { "/edit-feed" }
)
public class EditFeed extends HttpServlet {

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
        ServletContext context = getServletContext();
        HttpSession session = request.getSession();


        //Pass user id to MySQL class
        aggregator.MYSQL tc = new aggregator.MYSQL();


        session.setAttribute("checkboxes", tc.returnFeedCheckboxes(2));



        String newFeedName = "";
        String newFeedURL = "";

        Map<String, String[]> parameters = request.getParameterMap();
        for(String parameter : parameters.keySet()) {
            if(parameter.toLowerCase().startsWith("feeds")) {
                String[] values = parameters.get(parameter);

                if (0 < values.length) {
                    tc.deleteUserFeeds(2);
                    for (int i = 0; request.getParameterValues("feeds").length > i; i++) {
                        //out.println(request.getParameterValues("feeds")[i]);
                        tc.updateUserFeeds(2, Integer.parseInt(request.getParameterValues("feeds")[i]));

                    }
                }
            }

            // Create sql methods: addNewFeed
            if(parameter.startsWith("addFeedName")) {
                String[] addFeedNameValues = parameters.get(parameter);

                if (0 < addFeedNameValues.length) {
                    newFeedName = addFeedNameValues[0];
                }
            }
            if(parameter.startsWith("addFeedURL")) {
                String[] addFeedUrlValues = parameters.get(parameter);

                if (0 < addFeedUrlValues.length) {
                    newFeedURL = addFeedUrlValues[0];
                }
            }

        }
        if(newFeedName != "" &&  newFeedURL != "") {
            tc.insertNewFeedName(newFeedName);
            tc.insertNewFeedUrl(newFeedURL);
        }






        aggregator.FEEDS fd = new aggregator.FEEDS();
        try {
            session.setAttribute("feed", fd.getFeed("http://hosted2.ap.org/atom/APDEFAULT/3d281c11a96b4ad082fe88aa0db04305"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/edit-feed.jsp");
        dispatcher.forward(request, response);





    }

}
