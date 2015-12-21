package aggregatorTests;

import aggregator.Parse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 *  Tests for the Feed Model
 */
public class ParseTests {

    /* int Containing Feed ID */
    private int feedID;

    /* String Containing Feed Name */
    private String feedName;

    /* String Containing Feed URL */
    private String feedURL;

    /* Instantiate Parse */
    Parse parse = new Parse();


    /**
    *   Set initial values for testing
    *
     */
    @Before
    public void setup() {
        feedID = 1;
        feedName = "Test Feed";
        feedURL = "http://test.com";

        parse.setFeedID(feedID);
        parse.setFeedName(feedName);
        parse.setFeedURL(feedURL);
    }

    /**
    *   Test the Getter for Feed ID
    *
     */
    @Test
    public void testGetFeedID() {
        int returnedValue = parse.getFeedID();
        String testMessage = "FeedID should return 1, but instead returned " + returnedValue;

        assertTrue(testMessage, returnedValue == 1);
    }

    /**
    *   Test the Setter for Feed ID
    *
     */
    @Test
    public void testSetFeedID() {
        parse.setFeedID(3);
        int returnedValue = parse.getFeedID();
        String testMessage = "FeedID should return 3, but instead returned " + returnedValue;

        assertTrue(testMessage, returnedValue == 3);
    }

    /**
    *   Test the Getter for Feed Name
    *
     */
    @Test
    public void testGetFeedName() {
        String returnedValue = parse.getFeedName();
        String testMessage = "FeedName should return Test Feed, but instead returned " + returnedValue;

        assertTrue(testMessage, returnedValue == "Test Feed");
    }

    /**
    *   Test the Setter for Feed Name
    *
     */
    @Test
    public void testSetFeedName() {
        parse.setFeedName("New Test Feed");
        String returnedValue = parse.getFeedName();
        String testMessage = "FeedName should return New Test Feed, but instead returned "
                + returnedValue;

        assertTrue(testMessage, returnedValue == "New Test Feed");
    }

    /**
    *   Test the Getter for Feed URL
    *
     */
    @Test
    public void testGetFeedURL() {
        String returnedValue = parse.getFeedURL();
        String testMessage = "FeedName should return http://test.com, but instead returned "
                + returnedValue;

        assertTrue(testMessage, returnedValue == "http://test.com");
    }

    /*
    *   Test the Setter for Feed URL
    *
     */
    @Test
    public void testSetFeedURL() {
        parse.setFeedURL("http://newtest.com");
        String returnedValue = parse.getFeedURL();
        String testMessage = "FeedName should return http://newtest.com, but instead returned "
                + returnedValue;

        assertTrue(testMessage, returnedValue == "http://newtest.com");
    }

    /**
    *   Test the Parse Constructor
    *
     */
    @Test
    public void testConstructor() {

        Parse constructor = new Parse(5, "Constructor Test", "http://constructorTest");
        int testResultFeedID =  constructor.getFeedID();
        int failureCount = 0;
        String testResultFeedName = constructor.getFeedName();
        String testResultFeedURL = constructor.getFeedURL();
        String testMessage = "";

        /* Test the initialization of Feed ID */
        if (testResultFeedID != 5) {
            failureCount++;
            testMessage += "\r\rFailure " + failureCount
                    + ": FeedName should return 5, but instead returned "
                    + testResultFeedID;
        }

        /* Test the initialization of Feed Name */
        if (testResultFeedName != "Constructor Test") {
            failureCount++;
            testMessage  += "\r\rFailure " + failureCount
                    + ": FeedName should return Constructor Test, but instead returned "
                    + testResultFeedName;
        }

        /* Test the initialization of Feed URL */
        if (testResultFeedURL != "http://constructorTest") {
            failureCount++;
            testMessage += "\r\rFailure " + failureCount
                    + ": FeedName should return http://constrctorTest, but instead returned "
                    + testResultFeedURL;
        }

        assertTrue(testMessage, failureCount == 0);
    }

}

