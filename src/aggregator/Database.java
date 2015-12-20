package aggregator;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Database Controller
 *
 * @author Dedering
 */

public class Database {

    /* Database Host */
    private String host = "jdbc:mysql://localhost:3307/sample";

    /* Database Username */
    private String user = "root";

    /* Database Password */
    private String pass = "";

    /* Database Instance */
    private static Database instance = new Database();

    /* Connection */
    private Connection connection;

    /* Get Logger */
    private Logger logger = Logger.getLogger(this.getClass());

    /* Empty Constructor */
    private Database() {
    }

    /**
     * Get Database Instance
     *
     *@return
     */
    public static Database getInstance() {

        /* Return Instance */
        return instance;
    }

    /**
     * Get Connection
     *
     *@return
     */
    public Connection getConnection() {

        /* Return Connection */
        return connection;
    }

    /**
     * Connect to Database
     *  @exception ClassNotFoundException
     *
     */
    public void connect() throws Exception {

        /* Connect If No Current Connection */
        if (connection != null) {

            /* Log Connection Error */
            logger.error("Already Connected");

            /* Return w/o New Connection */
            return;
        }

        /* Try Catch Driver Connection */
        try {

            /* Try Driver */
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

            /* Log Driver Error */
            logger.error("Connection: Failure - Already Connected");

            /* Throw Driver Error */
            throw new Exception("Error: MySQL Driver not found");
        }

        /* Connect */
        connection = DriverManager.getConnection(host, user, pass);

        /* Log Connection Error */
        logger.info("Connection: Success");
    }
}