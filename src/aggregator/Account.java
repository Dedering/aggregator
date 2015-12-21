package aggregator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Map;

/**
 * Account - Handle form submissions from the My Account Page.
 *
 * @author Dedering
 */
public class Account {

    /* Create instance of FeedDao */
    private FeedDoa feedDao = new FeedDoa();

    /* Create instance of User Dao */
    USERDAO userDao = new USERDAO();

    /* Get Logger */
    private Logger logger = Logger.getLogger(this.getClass());

    /* Parameter Map */
    private Map<String, String[]> parameters;

    /* Incoming User Name */
    private String userName;

    /* Incoming Param Values */
    private String[] paramValues;

    /* Incoming User ID - default to 0 */
    private int userID = 0;

    /* String Containing Incoming Feed Name */
    String newFeedName = "";

    /* String Containing Incoming Feed URL */
    String newFeedURL = "";

    /**
     * Receive and Analyze the incoming parameters
     *
     * @param params
     * @param user_name
     * @param param_values
     * @throws SQLException
     */
    public void updateActiveFeeds(Map<String, String[]> params, String user_name, String[] param_values) throws SQLException {

        /* Allow global access to parameters map */
        parameters = params;

        /* Allow global access to user name */
        userName = user_name;

        /* Allow global access to param values */
        paramValues = param_values;

        /* Get user ID from userName */
        userID = userDao.getUserID(userName);

        /* Iterate Through Parameter Map */
        for(String parameter : parameters.keySet()) {

            /* Skip Parameters That Are Not Feed IDs */
            if (parameter.toLowerCase().startsWith("feeds")) {

                /* Inactivate all of the users feeds */
                inactivateFeeds(parameter);
            }

            /* Skip Parameters That Are Not New Feed Names */
            if(parameter.startsWith("addFeedName")) {

                /* Retrieve New Feed Name Param */
                accessFeedName(parameter);
            }

            /* Skip Parameters That Are Not New Feed URLs */
            if(parameter.startsWith("addFeedURL")) {

                /* Retrieve New Feed Name Param */
                accessFeedURL(parameter);
            }
        }

        /* Check that the Incoming Feed Name and URL params are not empty */
        if(newFeedName != "" &&  newFeedURL != "") {

            /* Add new feed to database */
            insertNewFeed();
        }

    }

    /**
     * Insert New Feed to Database
     *
     * @throws SQLException
     */
    public void insertNewFeed() throws SQLException {

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

    /**
     * Set Feed Name
     *
     * @param parameter
     * @throws SQLException
     */
    public void accessFeedName(String parameter) throws SQLException {

        /* Get Parameters */
        String[] addFeedNameValues = parameters.get(parameter);

        /* Check That Value Is Not Empty */
        if (0 < addFeedNameValues.length) {

            /* Get Parameter */
            newFeedName = addFeedNameValues[0];
        }
    }

    /**
     * Set Feed URL
     *
     * @param parameter
     * @throws SQLException
     */
    public void accessFeedURL(String parameter) throws SQLException {

        /* Get Parameters */
        String[] addFeedUrlValues = parameters.get(parameter);

        /* Check That Value Is Not Empty */
        if (0 < addFeedUrlValues.length) {

            /* Get Parameter */
            newFeedURL = addFeedUrlValues[0];
        }
    }

    /**
     * Inactive a users feeds prior to update
     *
     * @param parameter
     * @throws SQLException
     */
    public void inactivateFeeds(String parameter) throws SQLException {

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
            for (int i = 0; paramValues.length > i; i++) {

                /* Catch Exceptions */
                try {

                    /* Update Current User's Records In user_feeds Table */
                    feedDao.updateUserFeeds(userID, Integer.parseInt(paramValues[i]));
                } catch (SQLException e) {

                    /* Print Stack Trace */
                    e.printStackTrace();
                }
            }
        }

    }

}
