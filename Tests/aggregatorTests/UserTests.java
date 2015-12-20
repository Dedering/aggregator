package aggregatorTests;

import aggregator.USER;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;




public class UserTests {
    /* int Containing User ID */
    private int userID;

    /* String Containing User Name */
    private String userName;


    /* Instantiate Parse */
    USER user = new USER();


    /*
    *   Set initial values for testing
    *
     */
    @Before
    public void setup() {
        userID = 1;
        userName = "Test User";

        user.setUserID(userID);
        user.setUserName(userName);
    }

    /*
    *   Test the Getter for Feed ID
    *
     */
    @Test
    public void testUserID() {
        int returnedValue = user.getUserID();
        String testMessage = "UserID should return 1, but instead returned " + returnedValue;

        assertTrue(testMessage, returnedValue == 1);
    }

    /*
    *   Test the Setter for Feed ID
    *
     */
    @Test
    public void testSetUserID() {
        user.setUserID(3);
        int returnedValue = user.getUserID();
        String testMessage = "UserID should return 3, but instead returned " + returnedValue;

        assertTrue(testMessage, returnedValue == 3);
    }

    /*
    *   Test the Getter for Feed Name
    *
     */
    @Test
    public void testGetUserName() {
        String returnedValue = user.getUserName();
        String testMessage = "UserName should return Test User, but instead returned " + returnedValue;

        assertTrue(testMessage, returnedValue == "Test User");
    }

    /*
    *   Test the Setter for Feed Name
    *
     */
    @Test
    public void testSetUserName() {
        user.setUserName("New Test User");
        String returnedValue = user.getUserName();
        String testMessage = "UserName should return New Test User, but instead returned "
                + returnedValue;

        assertTrue(testMessage, returnedValue == "New Test User");
    }


    /*
    *   Test the Parse Constructor
    *
     */
    @Test
    public void testConstructor() {

        USER constructor = new USER(5, "Test User Name");
        int failureCount = 0;
        int testResultUserID =  constructor.getUserID();
        String testResultUserName = constructor.getUserName();
        String testMessage = "";

        /* Test the initialization of Feed ID */
        if (testResultUserID != 5) {
            failureCount++;
            testMessage += "\r\rFailure " + failureCount
                    + ": UserID should return 5, but instead returned "
                    + testResultUserID;
        }

        /* Test the initialization of Feed Name */
        if (testResultUserName != "Test User Name") {
            failureCount++;
            testMessage  += "\r\rFailure " + failureCount
                    + ": UserName should return Constructor Test, but instead returned "
                    + testResultUserName;
        }


        assertTrue(testMessage, failureCount == 0);
    }

}

