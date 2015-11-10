<%--
  Created by IntelliJ IDEA.
  User: Private
  Date: 11/1/15
  Time: 5:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html>
<head>
  <%@ include file="/file-que.jsp" %>
  <title>My Feed</title>
</head>
<body>
<%@ include file="/user-nav.jsp" %>





<% //Pass user id to MySQL class
  aggregator.MYSQL tc = new aggregator.MYSQL();
  out.print(tc.returnUserFeeds(2));

  aggregator.FEEDS fd = new aggregator.FEEDS();
  out.println(fd.getFeed("http://hosted2.ap.org/atom/APDEFAULT/3d281c11a96b4ad082fe88aa0db04305"));
  //aggregator.FEEDS fd = new aggregator.FEEDS();

 // out.print(fd.returnUserFeeds("http://hosted2.ap.org/atom/APDEFAULT/3d281c11a96b4ad082fe88aa0db04305"));
%>



</body>
</html>
