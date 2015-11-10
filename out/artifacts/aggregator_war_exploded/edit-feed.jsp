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


<form action="/edit-feed.jsp" method="post">

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
  }


  out.println("<h2>My Feeds</h2>");
    out.println(tc.returnUserFeeds(2));
    out.println("<h2>Add Feeds</h2>");
    out.println(tc.returnFeedCheckboxes(2));



  %>
  <input type="hidden" name="feeds" value="0" />

  <input type="submit" value="update" />

</form>


</body>
</html>
