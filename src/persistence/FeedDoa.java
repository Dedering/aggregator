package persistence;

import models.Parse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Feed Dao
 *
 * @author Dedering
 */
public class FeedDoa {

    /**
     * Return User's Edit Feed Checkboxes
     *
     * @param
     * @return
     */
    public String returnFeedCheckboxes(int user_id) throws SQLException {

        /* Get Database Connection */
        Connection connection = Database.getInstance().getConnection();

        /* Create Connection Statement */
        Statement selectStatement = connection.createStatement();

        /* Query String */
        String sql = "Select feed_name, feed_id from feed;";

        /* Execute Query */
        ResultSet results = selectStatement.executeQuery(sql);

        /* Retrieve CSV of Current User's Feed */
        ArrayList<String> userfeeds = returnUserFeedCSV(user_id);

        /* Return String */
        String checkboxes = "";

        /* Iterate Through Results */
        while (results.next()) {

            /* Get Feed Name From Results */
            String feed_name = results.getString("feed_name");

            /* Get Feed ID From Results */
            String feed_id = results.getString("feed_id");

            /* Retrieve CSV of Current User's Active Feeds */
            if(userfeeds.indexOf(feed_name) >= 0) {

                /* If Feed is Active, Checkbox is Checked */
                checkboxes += "<br /><label>" + feed_name + "<input type=\"checkbox\" name=\"feeds\" value=\"" + feed_id + "\" checked/></label>";
            } else {

                /* If Feed is In-active, Checkbox is Not Checked */
                checkboxes += "<br /><label>" + feed_name + "<input type=\"checkbox\" name=\"feeds\" value=\"" + feed_id + "\" /></label>";
            }
        }

        /* Close Results */
        results.close();

        /* Close Statement */
        selectStatement.close();

        /* Return Checkboxes */
        return checkboxes;
    }

    /**
     * Return CSV of User's Feed Names
     *
     * @param
     * @return
     */
    public ArrayList returnUserFeedCSV(int user_id) throws SQLException {

        /* Get Database Connection */
        Connection connection = Database.getInstance().getConnection();

        /* Create Connection Statement */
        Statement selectStatement = connection.createStatement();

        /* Create ArrayList of User's Active Feeds */
        ArrayList<String> activeFeeds = new ArrayList<>();

        /* Query String */
        String sql = "Select feed_name from feed Where feed_id IN (Select feed_id From user_feeds Where user_id IN (Select user_id From users Where user_id = " + user_id + "));";

        /* Execute Query */
        ResultSet results = selectStatement.executeQuery(sql);

        /* Iterate Through Results */
        while (results.next()) {

            /* Get Feed Name from Results */
            String feed_name = results.getString("feed_name");

            /* Add Feed Name to ArrayList of Active Feeds */
            activeFeeds.add(feed_name);
        }

        /* Close Results */
        results.close();

        /* Close Statement */
        selectStatement.close();

        /* Return Arraylist */
        return activeFeeds;
    }

    /**
     * Add Feed to User's Account
     *
     * @param
     * @return
     */
    public void updateUserFeeds(int user_id, int feed_id) throws SQLException {

        /* Get Database Connection */
        Connection connection = Database.getInstance().getConnection();

        /* Create Connection Statement */
        Statement selectStatement = connection.createStatement();

        /* Query String */
        String sql = "insert into user_feeds (user_id,feed_id) VALUES(" + user_id + "," + feed_id + ");";

        /* Execute Update */
        selectStatement.executeUpdate(sql);

        /* Close Statement */
        selectStatement.close();

    }

    /**
     * Insert New Feed
     *
     * @param
     * @return
     */
    public void insertNewFeed(String feed_name, String feed_url) throws SQLException {

        /* Get Database Connection */
        Connection connection = Database.getInstance().getConnection();

        /* Create Connection Statement */
        Statement selectStatement = connection.createStatement();

        /* Query String */
        String sql = "INSERT INTO feed (feed_name, feed_url) VALUES ('" + feed_name + "', '" + feed_url + "');";

        /* Execute Update */
        selectStatement.executeUpdate(sql);

        /* Close Statement */
        selectStatement.close();

    }

    /**
     * Delete User's Feeds by UserID
     *
     * @param
     * @return
     */
    public void deleteUserFeeds(int userID) throws SQLException {

        /* Get Database Connection */
        Connection connection = Database.getInstance().getConnection();

        /* Create Connection Statement */
        Statement selectStatement = connection.createStatement();

        /* Query String */
        String sql = "DELETE FROM user_feeds WHERE user_id = " + userID + ";";

        /* Execute Update */
        selectStatement.executeUpdate(sql);

        /* Close Statement */
        selectStatement.close();

    }

    /**
     * Return List of User's Feeds by UserID
     *
     * @param
     * @return
     */
    public List<Parse> getFeeds(int userID) throws SQLException {

        /* Get Database Connection */
        Connection connection = Database.getInstance().getConnection();

        /* Create Connection Statement */
        Statement selectStatement = connection.createStatement();

        /* Create ArrayList of Feeds */
        List<Parse> feeds = new ArrayList<>();

        /* Query String */
        String sql = "select a.feed_id, b.feed_name, b.feed_url from user_feeds a Natural Join feed b Where user_id = " + userID;

        /* Execute Query */
        ResultSet results = selectStatement.executeQuery(sql);

        /* Iterate Through Results */
        while (results.next()) {

            /* Get Feed ID From Results */
            int feedID = results.getInt("feed_id");

            /* Get Feed Name From Results */
            String feedName = results.getString("feed_name");

            /* Get Feed URL From Results */
            String feedUrl = results.getString("feed_url");

            /*   */
            Parse feed = new Parse(feedID, feedName, feedUrl);
            feeds.add(feed);
        }

        /* Close Results */
        results.close();

        /* Close Statement */
        selectStatement.close();

        /* Return Feeds Arraylist */
        return feeds;
    }
}