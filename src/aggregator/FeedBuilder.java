package aggregator;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Feed Builder - Constructs the checkboxes for the My Account Page
 *
 * @author Dedering
 */
public class FeedBuilder {

    /* Initialize Logger */
    private Logger logger;

    /* Initialize default user ID */
    private int userID = 0;

    /* Initialize feeds list */
    private List<Parse> feeds = null;

    /* Create Instance of FeedDoa */
    FeedDoa feedDao;

    /**
     * Empty Constructor
     *
     */
    public FeedBuilder() {

        /* Get Logger */
        logger = Logger.getLogger(this.getClass());
    }

    /**
     * Constructor
     * @param userID
     *
     */
    public FeedBuilder(int userID) throws SQLException {

        /* Set userID */
        this.userID = userID;

        /* Instantiate FeedDao */
        this.feedDao = new FeedDoa();

        /* Configure Logger */
        BasicConfigurator.configure();

        /* Instantiate Logger */
        logger = Logger.getLogger(this.getClass());

        /* Catch exceptions */
        try {

            /* Get Feeds from FeedDao */
            feeds = feedDao.getFeeds(userID);

        } catch (SQLException e) {

            /* Print Stack Trace */
            e.printStackTrace();
        }

    }
    /**
     *
     * Iterate Through the List of Feeds, Return the Feed Nav
     *
     * @return
     * @throws Exception
     */

    public String feedLinks() throws Exception {

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
     * @return
     * @throws Exception
     */
    public String parseFeed() throws Exception {

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

            /* Arraylist of Incoming <decription> Element Values */
            ArrayList<String> descriptions = new ArrayList<>();

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
                logger.info("Feed: " + name + " - " + pair.getValue());

                /* Remove Current Iterator Position */
                it.remove();
            }
        }

        /* Return Feed */
        return feed;
    }
}