package aggregator;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Feed Controller
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

        /* Logging Configuration */
        BasicConfigurator.configure();

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

        /* Arraylist of Incoming <link> Element Values */
        List<Parse> feeds = null;

        /* Catch Exceptions */
        try {

            /* Get Database Connection */
            Database.getInstance().connect();

            /* Get User ID */
            userID = dao.getUserID(userName);

            /* Get User Feed */
            feeds = feedDao.getFeeds(userID);

            /* Set Bean - feedLinks */
            session.setAttribute("feedLinks", feedLinks(feeds));

            /* Set Bean - feed */
            session.setAttribute("feed", parseFeed(feeds));

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

    /**
     *
     * Iterate Through List of Feeds, Return Feed Nav
     *
     * @param feeds
     * @return
     * @throws Exception
     */

    public String feedLinks(List<Parse> feeds) throws Exception {

        /* Open List */
        String feedNav = "<ul class='feed-nav'>";

        /* Iterate Through List of Feeds */
        for (int i = 0; i < feeds.size(); i++) {

            /* String Containing Feed Name */
            String name = feeds.get(i).getFeedName();

            /* Construct Feed Navigation List, Single Page Anchor Links */
            feedNav += "<li><a href='#" + name + "'>" + name + "</a></li>";
        }

        /* Close List */
        feedNav += "</ul>";

        /* Return Feed Nav */
        return feedNav;
    }

    /**
     *
     * Iterate Through List of Feeds, Return HTML Output
     *
     * @param feeds
     * @return
     * @throws Exception
     */

    public String parseFeed(List<Parse> feeds) throws Exception {

        /* String Containing Return Feed */
        String feed = "";

        /* Iterate Through List of Feeds */
        for (int i = 0; i < feeds.size(); i ++) {

            /* String Containing Feed Name */
            String name = feeds.get(i).getFeedName();

            /* URL Containing Feed URL */
            URL url = new URL(feeds.get(i).getFeedURL());

            /* Open Buffered Reader Stream */
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            /* Arraylist of Incoming <link> Element Values */
            ArrayList<String> links = new ArrayList<>();

            /* Arraylist of Incoming <title> Element Values */
            ArrayList<String> titles = new ArrayList<>();

            /* Hashmap of Parsed Links(K) and Titles(V) */
            HashMap<String, String> matches = new HashMap<>();

            /* String Containing Input Value */
            String inputLine;

            /* Iterate Line by Line Through the Incoming Feed */
            while ((inputLine = in.readLine()) != null) {

                /* Pattern - Input Contains Hyperlink */
                Pattern ahrefPattern = Pattern.compile("<a class=\"url entry-title\".*?href=\"(.*?)\".*?>(.*?)</a>");

                /* Pattern - Input Contains <link> */
                Pattern linkPattern = Pattern.compile("<link.*?>(.*?)</link>");

                /* Pattern - Input Contains <title> */
                Pattern titlePattern = Pattern.compile("<title.*?>(.*?)</title>");

                /* Matcher - Input Contains Hyperlink */
                Matcher ahrefMatch = ahrefPattern.matcher(inputLine);

                /* Matcher - Input Contains <link> */
                Matcher linkMatch = linkPattern.matcher(inputLine);

                /* Matcher - Input Contains <title> */
                Matcher titleMatch = titlePattern.matcher(inputLine);


                /* Input Matched Hyperlink - Used For NYT */
                while(ahrefMatch.find()) {

                    /* Add Hyperlink Match to Hashmap */
                    matches.put(ahrefMatch.group(1), ahrefMatch.group(2));
                }

                /* Input Matched <title> - Used For AP */
                while(titleMatch.find()) {

                    /* Add <title> Match to Array */
                    titles.add(titleMatch.group(1));
                }

                /* Input Matched <link> - Used For AP */
                while(linkMatch.find()) {

                    /* Add <link> Match to Array */
                    links.add(linkMatch.group(1));
                }
            }

            /* Close Buffered Reader Stream */
            in.close();

            /* Add Feed Name to Feed Return */
            feed += "<h3 id='" + name + "'>" + name + "</h3>";

            /* Iterate Through Array of Links */
            for (int x = 0;x < links.size(); x++) {

                /* Add <link> & <title> elements to Hashmap */
                matches.put(links.get(x), titles.get(x));
            }

            /* Create Iterator */
            Iterator it = matches.entrySet().iterator();

            /* Iterate through Hashmap of Links(K) and Titles(V) */
                while (it.hasNext()) {

                    /* Advance Iterator */
                    Map.Entry pair = (Map.Entry)it.next();

                    /* Add K,V to Feed Return */
                    feed += "<p><a href=\"" + pair.getKey() + "\"  target=\"_blank\">" + pair.getValue() + "</a></p>";

                    /* Log Feed Output */
                    logger.info("User: " + userName + " | Feed: " + name + " - " + pair.getValue());

                    /* Remove Current Iterator Position */
                    it.remove();
            }
        }

        /* Return Feed */
        return feed;
    }
}