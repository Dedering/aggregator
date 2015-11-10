package aggregator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class FEEDS {
    String feed = "";


    public void parseFeed(String url) throws Exception {
        URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        feed += "<h3>" + url + "</h3>";

        while ((inputLine = in.readLine()) != null)
            //System.out.println(inputLine);
            feed += inputLine;

        in.close();
    }

    public String getFeed(String url) throws Exception {

        aggregator.MYSQL mysql = new aggregator.MYSQL();

     //   feed += mysql.returnUserFeedUrls(2);
/*
        String string = mysql.returnUserFeedUrls(2);
        String[] parts = string.split("|||");
        feed += "1-" + parts[0];
        feed += "2-" + parts[1];
*/
      /*  for (int i = 0; parts.length > i; i++) {
            feed += parts[i];

        }*/
        String feedIDs = mysql.returnUserFeedIDs(2);
        String feedIDArray[] = feedIDs.split(",");
        int feed_id = 0;

     /*   for (int i = 0; i < feedIDArray.length; i++) {

            feed += "---" + feedIDArray[i] + "---";

            feed += mysql.returnFeedNameByID(feedIDArray[i]);


        }*/


            String string = mysql.returnUserFeedUrls(2);
        String str[] = string.split("<split>");

        for (int i = 0; i < str.length; i++) {
          //  String arr[] = str[i].split(":");
         //   findReplaceValues.put(arr[0], arr[1]);
           //  feed_id = Integer.parseInt(feedIDArray[i]);
          //  String feed_d_s = feedIDArray[i];
           // feed_id = Integer.parseInt(feed_d_s);
        //    feed += mysql.returnFeedNameByID(feed_id);

            feed += "<h1>---" + feedIDArray[i] + "---</h1>";

       //     feed += "---" + str[i] + "---";
            //feed += mysql.returnFeedNameByID(feed_id);
            parseFeed(str[i]);

        }

        //parseFeed(url);
        System.out.println(feed);
        //feed;





        return "<div class=\"feed\">" + feed + "</div>";
    }


}