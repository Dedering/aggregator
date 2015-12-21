package aggregator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * USERDAO - Handle User Related SQL operations
 *
 * @author Dedering
 */
public class USERDAO {

    /**
     * Return User Object
     * @param remoteUser
     * @return
     * @throws SQLException
     */
    public List<USER> getUser(String remoteUser) throws SQLException {

        /* Arraylist of User Objects */
        List<USER> users = new ArrayList<USER>();

        /* Get Connection */
        Connection connection = Database.getInstance().getConnection();

        /* Query String */
        String sql = "select * from users where user_name = '" + remoteUser + "'";

        /* Create Connection Statement */
        Statement selectStatement = connection.createStatement();

        /* Execute Query */
        ResultSet results = selectStatement.executeQuery(sql);

        /* Iterate Through Results */
        while (results.next()) {

            /* User ID */
            int userID = results.getInt("user_id");

            /* User Name */
            String userName = results.getString("user_name");

            /* Create User Object of Result */
            USER user = new USER(userID, userName);

            /* Add User Object to Arraylist of User Objects */
            users.add(user);
        }

        /* Close Results */
        results.close();

        /* Close Statement */
        selectStatement.close();

        /* Return ArrayList of User Objects */
        return users;
    }

    /**
     * Get the Current Users from User List
     *
     * @param remoteUser
     * @return
     * @throws SQLException
     */
    public String getUSER(String remoteUser) throws SQLException {

        /* Get List of Users */
        List<USER> users = getUser(remoteUser);

        /* Get the value of the first result */
        String user = String.valueOf(users.get(0));

        /* Return the result */
        return user;
    }

    /**
     * Get the Current User's User ID
     *
     * @param remoteUser
     * @return
     * @throws SQLException
     */
    public int getUserID(String remoteUser) throws SQLException {

        /* Get List of Users */
        List<USER> users = getUser(remoteUser);

        /* Get the userId of the first result */
        int user = users.get(0).getUserID();

        /* Return the userName */
        return user;
    }
}