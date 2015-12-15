<%--

  Index Page
  @author Dedering

--%>

<%-- UTF 8 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- HTML 5 --%>
<!DOCTYPE html>

<html>
  <head>

    <%-- Include Script Queue --%>
    <%@ include file="/file-que.jsp" %>

    <%-- Title --%>
    <title>News Aggregator</title>

  </head>
  <body>

  <%-- Redirect To Feed --%>
  <%
    String site = new String("/feed");
    response.setStatus(response.SC_MOVED_PERMANENTLY);
    response.setHeader("Location", site);
  %>
  </body>
</html>
