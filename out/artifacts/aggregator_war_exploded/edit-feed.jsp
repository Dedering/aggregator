<%@ page import="java.util.*" %>
<%@ page import = "java.util.Map" %>

<%@ page import="java.util.List,java.util.ArrayList,java.util.Iterator"%>
<%--
  Created by IntelliJ IDEA.
  User: Private
  Date: 11/1/15
  Time: 10:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%@ include file="/file-que.jsp" %>

  <title></title>
</head>
<body>
<%@ include file="/user-nav.jsp" %>
Edit Feed


<%
/*
  String token = request.getParameter("test");
  if (token != null) {
    out.println(token);
  }
*/





%>

<%
/*
  Enumeration headerNames = request.getHeaderNames();
  while(headerNames.hasMoreElements()) {
    String paramName = (String)headerNames.nextElement();
    out.print("<tr><td>" + paramName + "</td>\n");
    String paramValue = request.getHeader(paramName);
    out.println("<td> " + paramValue + "</td></tr>\n");
  }
*/
%>
<div class="container">
    <div class="row">




<form action="/edit-feed.jsp" method="get">

  <% //Pass user id to MySQL class
    aggregator.MYSQL tc = new aggregator.MYSQL();

    // Pass feed list to update method
 /*   if (null != request){ //} && request.getContentType() != null) {
        //String feedlist[] = request.getParameterValues("feeds");
       // int count = feedlist.length;

      //if (request.getParameterValues("feeds") !=null && !request.getParameterValues("feeds").isEmpty()) {

      int i;
      for (i = 0; request.getParameterValues("feeds").length > i; i++) {
          //tc.updateUserFeeds(2, Integer.parseInt(request.getParameterValues("feeds")[i]));
          out.println(request.getParameterValues("feeds")[i]);
        }

      }*/



      String newFeedName = "";
      String newFeedURL = "";

  Map<String, String[]> parameters = request.getParameterMap();
  for(String parameter : parameters.keySet()) {
        if(parameter.toLowerCase().startsWith("feeds")) {
            String[] values = parameters.get(parameter);

            if (0 < values.length) {
              tc.deleteUserFeeds(2);
              for (int i = 0; request.getParameterValues("feeds").length > i; i++) {
                //out.println(request.getParameterValues("feeds")[i]);
                tc.updateUserFeeds(2, Integer.parseInt(request.getParameterValues("feeds")[i]));

              }
            }
        }

      // Create sql methods: addNewFeed
      if(parameter.startsWith("addFeedName")) {
          String[] addFeedNameValues = parameters.get(parameter);

          if (0 < addFeedNameValues.length) {
              newFeedName = addFeedNameValues[0];
          }
      }
      if(parameter.startsWith("addFeedURL")) {
          String[] addFeedUrlValues = parameters.get(parameter);

          if (0 < addFeedUrlValues.length) {
              newFeedURL = addFeedUrlValues[0];
          }
      }
      /*
      if(parameter.toLowerCase().startsWith("addFeedURL")) {
          String[] addFeedUrl = parameters.get(parameter);

          if (0 < addFeedUrl.length) {
              tc.deleteUserFeeds(addFeedUrl[0]);

          }
      }
      */
  }
      if(newFeedName != "" &&  newFeedURL != "") {
          tc.insertNewFeedName(newFeedName);
          tc.insertNewFeedUrl(newFeedURL);
      }




      out.println("<div class=\"col-sm-4\" id=\"cell-one\">");
        out.println("<h2>Current Feed</h2>");
        out.println(tc.returnUserFeeds(2));
      out.println("</div>");


      out.println("<div class=\"col-sm-4\" id=\"cell-two\">");
        out.println("<h2>Choose Feeds</h2>");
        out.println(tc.returnFeedCheckboxes(2));



  %>

    <a href="#" id="add-another">Add Another Feed</a>

    </div>

    <div class="col-sm-4" id="cell-three">

    <h2>Add Another Feed</h2>

    <label>Feed Name<br />
        <input type="text" name="addFeedName" placeholder="Feed Name" />
    </label><br />

    <label>RSS Url<br />
        <input type="text" name="addFeedURL" placeholder="RSS Url"  />
    </label>
    <br />
    <br />


    </div>
    <input type="hidden" name="feeds" value="0" />

    <input type="submit" value="update" />
</form>


    </div>
</div>







<script type="text/javascript" src="/assets/jQuery.js"></script>
<script type="text/javascript" src="/js/site.js"></script>

</body>
</html>
