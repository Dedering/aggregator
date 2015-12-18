package persistence;

import models.USER;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import org.hibernate.metamodel.relational.Database;

/**
 * Created by Private on 12/5/15.
 */
public class USERDAO {



        public List<USER> getUser(String remoteUser) throws SQLException {

            List<USER> users = new ArrayList<USER>();

            Connection connection = Database.getInstance().getConnection();

            String sql = "select * from users where user_name = '" + remoteUser + "'";
            Statement selectStatement = connection.createStatement();

            ResultSet results = selectStatement.executeQuery(sql);

            while (results.next()) {
                int userID = results.getInt("user_id");
                String userName = results.getString("user_name");

                USER user = new USER(userID, userName);
                users.add(user);
            }

            results.close();
            selectStatement.close();

            return users;
        }



        public String getUSER(String remoteUser) throws SQLException {
            List<USER> users = getUser(remoteUser);
            String user = String.valueOf(users.get(0));
            return user;
        }

    public int getUserID(String remoteUser) throws SQLException {
        List<USER> users = getUser(remoteUser);
        int user = users.get(0).getUserID();
        return user;
    }


    public String getUserName() {
        return "success";
    }




}