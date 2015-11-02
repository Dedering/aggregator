<%--
  Created by IntelliJ IDEA.
  User: Private
  Date: 11/1/15
  Time: 6:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html>
<head>
  <%@ include file="/file-que.jsp" %>

  <title></title>
</head>
<body>

<%@ page session="true"%>

User '<%=request.getRemoteUser()%>' has been logged out.

<% session.invalidate(); %>

<br/><br/>
<a href="/index.jsp?logout=success">Click here to return to the homepage</a>


</body>
</html>
