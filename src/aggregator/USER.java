package aggregator;

/**
 * USER Model
 *
 * @author Dedering
 */
public class USER {

    /* int Containing User ID */
    private int userID;

    /* String Containing User Name */
    private String userName;

    /**
    * Empty Constructor
    *
    */
    public USER() {
    }

    /**
     * Constructor
     * @param userName
     * @param userID
     *
     */
    public USER(int userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    /**
     *
     * Get userID
     * @return
     */
    public int getUserID() {

        /* Return userID */
        return userID;
    }

    /**
     *
     * Set userID
     * @param userID
     */
    public void setUserID(int userID) {

        /* Update userID Value */
        this.userID = userID;
    }

    /**
     *
     * Get userName
     * @return
     */
    public String getUserName() {

        /* Return userName */
        return userName;
    }

    /**
     *
     * Set userName
     * @param userName
     */
    public void setUserName(String userName) {

        /* Update userName Value */
        this.userName = userName;
    }
}