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
@WebServlet(
        name = "Feed",
        urlPatterns = { "/feed" }
)
public class Feed extends HttpServlet {

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
        session.setAttribute("feedList", tc.returnUserFeeds(2));

        aggregator.FEEDS fd = new aggregator.FEEDS();
        try {
            session.setAttribute("feed", fd.getFeed("http://hosted2.ap.org/atom/APDEFAULT/3d281c11a96b4ad082fe88aa0db04305"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/feed.jsp");
        dispatcher.forward(request, response);





    }

}
