package aggregator;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 *
 *@author     Eric Knapp
 *
 */
public class MYSQL {


    // return feed checkboxes

    public String returnFeedCheckboxes(int user_id){
        String feeds = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> userfeeds = returnUserFeedCSV(user_id);

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/sample", "root", "");

            statement = connection.createStatement();
//            String queryString = "SELECT user_name, user_id FROM users ";
            String queryString = "Select feed_name, feed_id from feed;";

            System.out.println("queryString: " + queryString);

            resultSet = statement.executeQuery(queryString);

            System.out.println();

            while (resultSet.next()) {
                String feed_name = resultSet.getString("feed_name");
                String feed_id = resultSet.getString("feed_id");

                if(userfeeds.indexOf(feed_name) >= 0) {
                    feeds += "<br /><label>" + feed_name + "<input type=\"checkbox\" name=\"feeds\" value=\"" + feed_id + "\" checked/></label>";
                } else {
                    feeds += "<br /><label>" + feed_name + "<input type=\"checkbox\" name=\"feeds\" value=\"" + feed_id + "\" /></label>";
                }


            }

        } catch (ClassNotFoundException classNotFound) {
            System.err.println("Cannot find database driver ");
            classNotFound.printStackTrace();
        } catch (SQLException sqlException) {
            System.err.println("Error in connection.ecting to database "
                    + sqlException);
            sqlException.printStackTrace();
        } catch (Exception exception) {
            System.err.println("General Error");
            exception.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                System.err.println("Error in connection.ecting to database "
                        + sqlException);
                sqlException.printStackTrace();
            } catch (Exception exception) {
                System.err.println("General Error");
                exception.printStackTrace();
            }
        }
        return feeds;

    }








    // return feeds by id
    public String returnUserFeeds(int user_id){
        String feeds = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/sample", "root", "");

            statement = connection.createStatement();
//            String queryString = "SELECT user_name, user_id FROM users ";
            String queryString = "Select feed_name from feed Where feed_id IN (Select feed_id From user_feeds Where user_id IN (Select user_id From users Where user_id = " + user_id + "));";

            System.out.println("queryString: " + queryString);

            resultSet = statement.executeQuery(queryString);

            System.out.println();

            while (resultSet.next()) {
                String feed_name = resultSet.getString("feed_name");

                feeds += "<br />" + feed_name;

            }

        } catch (ClassNotFoundException classNotFound) {
            System.err.println("Cannot find database driver ");
            classNotFound.printStackTrace();
        } catch (SQLException sqlException) {
            System.err.println("Error in connection.ecting to database "
                    + sqlException);
            sqlException.printStackTrace();
        } catch (Exception exception) {
            System.err.println("General Error");
            exception.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                System.err.println("Error in connection.ecting to database "
                        + sqlException);
                sqlException.printStackTrace();
            } catch (Exception exception) {
                System.err.println("General Error");
                exception.printStackTrace();
            }
        }
        return feeds;

    }















    // update user feeds
    public String updateUserFeeds(int user_id,int feed_id){
        String feeds = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/sample", "root", "");

            statement = connection.createStatement();
//            String queryString = "SELECT user_name, user_id FROM users ";
            String queryString = "Select feed_name from feed Where feed_id IN (Select feed_id From user_feeds Where user_id IN (Select user_id From users Where user_id = " + user_id + "));";

            System.out.println("queryString: " + queryString);

            resultSet = statement.executeQuery(queryString);

            System.out.println();

            while (resultSet.next()) {
                String feed_name = resultSet.getString("feed_name");

                feeds += "<br />" + feed_name;

            }

        } catch (ClassNotFoundException classNotFound) {
            System.err.println("Cannot find database driver ");
            classNotFound.printStackTrace();
        } catch (SQLException sqlException) {
            System.err.println("Error in connection.ecting to database "
                    + sqlException);
            sqlException.printStackTrace();
        } catch (Exception exception) {
            System.err.println("General Error");
            exception.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                System.err.println("Error in connection.ecting to database "
                        + sqlException);
                sqlException.printStackTrace();
            } catch (Exception exception) {
                System.err.println("General Error");
                exception.printStackTrace();
            }
        }
        return feeds;

    }


















    // return csv of feeds by id
    public ArrayList returnUserFeedCSV(int user_id){
        ArrayList<String> feeds = new ArrayList<String>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/sample", "root", "");

            statement = connection.createStatement();
//            String queryString = "SELECT user_name, user_id FROM users ";
            String queryString = "Select feed_name from feed Where feed_id IN (Select feed_id From user_feeds Where user_id IN (Select user_id From users Where user_id = " + user_id + "));";

            System.out.println("queryString: " + queryString);

            resultSet = statement.executeQuery(queryString);

            System.out.println();

            while (resultSet.next()) {
                String feed_name = resultSet.getString("feed_name");

                feeds.add(feed_name);

            }

        } catch (ClassNotFoundException classNotFound) {
            System.err.println("Cannot find database driver ");
            classNotFound.printStackTrace();
        } catch (SQLException sqlException) {
            System.err.println("Error in connection.ecting to database "
                    + sqlException);
            sqlException.printStackTrace();
        } catch (Exception exception) {
            System.err.println("General Error");
            exception.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                System.err.println("Error in connection.ecting to database "
                        + sqlException);
                sqlException.printStackTrace();
            } catch (Exception exception) {
                System.err.println("General Error");
                exception.printStackTrace();
            }
        }
        return feeds;

    }





    /**
     *  The main program for the JDBCSelectWhereExample class
     *
     *@param  args  The command line arguments
     *
     *@since
     *
     */
    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/sample", "root", "");

            statement = connection.createStatement();
            String name = "Smith";
            String queryString = "SELECT user_name FROM users ";

            System.out.println("queryString: " + queryString);

            resultSet = statement.executeQuery(queryString);

            System.out.println();

            while (resultSet.next()) {
                String user_name = resultSet.getString("user_name");

                System.out.println(" Row: " + user_name);

            }

            System.out.println();



/*
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columns = resultSetMetaData.getColumnCount();
            String nameOne = resultSetMetaData.getColumnName(1);
            String typeOne = resultSetMetaData.getColumnTypeName(1);
            String labelOne = resultSetMetaData.getColumnLabel(1);
            System.out.println(" Column count : " + columns);
            System.out.println(" Column 1 name : " + nameOne);
            System.out.println(" Column 1 type : " + typeOne);
            System.out.println(" Column 1 label name : " + labelOne);

            System.out.println();
*/
        } catch (ClassNotFoundException classNotFound) {
            System.err.println("Cannot find database driver ");
            classNotFound.printStackTrace();
        } catch (SQLException sqlException) {
            System.err.println("Error in connection.ecting to database "
                    + sqlException);
            sqlException.printStackTrace();
        } catch (Exception exception) {
            System.err.println("General Error");
            exception.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                System.err.println("Error in connection.ecting to database "
                        + sqlException);
                sqlException.printStackTrace();
            } catch (Exception exception) {
                System.err.println("General Error");
                exception.printStackTrace();
            }
        }

    }

}
