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
<%@ include file="/user-nav.jsp" %>





<% //Pass user id to MySQL class
  aggregator.MYSQL tc = new aggregator.MYSQL();
  out.print(tc.returnUserFeeds(2));
%>



</body>
</html>
