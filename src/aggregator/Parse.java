package aggregator;

/**
 * Created by Private on 12/6/15.
 */
public class Feeds {

    private int feedID;
    private String feedName;
    private String feedURL;


    public Feeds() {
    }

    public Feeds(int feedID, String feedName, String feedURL) {
        this.feedID = feedID;
        this.feedName = feedName;
        this.feedURL = feedURL;
    }

    public int setFeedID() {
        return feedID;
    }

    public void setFeedID(int feedID) {
        this.feedID = feedID;
    }

    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }


    public String getFeedURL() {
        return feedURL;
    }

    public void setFeedURL(String feedURL) {
        this.feedURL = feedURL;
    }

    public String toString() {
        return  System.lineSeparator() +
                "Feed: " + " "
                + feedID + " "
                + feedName + " "
                + feedURL;
    }
}