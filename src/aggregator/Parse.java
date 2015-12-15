package aggregator;

/**
 * Feed Model
 *
 * @author Dedering
 */
public class Parse {

    /* int Containging Feed ID */
    private int feedID;

    /* String Containging Feed Name */
    private String feedName;

    /* String Containging Feed URL */
    private String feedURL;

    /**
     *
     * Empty Constructor
     *
     */
    public Parse() {
    }

    /**
    *
    * Constructor
    * @param
    */
    public Parse(int feedID, String feedName, String feedURL) {

        /* Initialize feedID */
        this.feedID = feedID;

        /* Initialize feedName */
        this.feedName = feedName;

        /* Initialize feedURL  */
        this.feedURL = feedURL;
    }

    /**
     *
     * Get feedID
     * @return
     */
    public int getFeedID() {

        /* Return feedID */
        return feedID;
    }
    /**
     *
     * Set feedID
     * @param
     */
    public void setFeedID(int feedID) {

        /* Update feedID Value */
        this.feedID = feedID;
    }

    /**
     *
     * Get feedName
     * @return
     */
    public String getFeedName() {

        /* Return feedName */
        return feedName;
    }

    /**
     *
     * Set feedID
     * @param
     */
    public void setFeedName(String feedName) {

        /* Update feedName Value */
        this.feedName = feedName;
    }

    /**
     *
     * Get feedURL
     * @return
     */
    public String getFeedURL() {

        /* Return feedURL */
        return feedURL;
    }

    /**
     *
     * Set feedURL
     * @param
     */
    public void setFeedURL(String feedURL) {

        /* Update feedURL Value */
        this.feedURL = feedURL;
    }
}